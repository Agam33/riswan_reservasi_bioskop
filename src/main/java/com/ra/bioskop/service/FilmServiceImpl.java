package com.ra.bioskop.service;

import com.ra.bioskop.dto.mapper.FilmMapper;
import com.ra.bioskop.dto.model.film.FilmDTO;
import com.ra.bioskop.dto.model.film.ScheduleDTO;
import com.ra.bioskop.exception.ExceptionType;
import com.ra.bioskop.model.film.Film;
import com.ra.bioskop.model.film.Schedule;
import com.ra.bioskop.repository.FilmRepository;
import com.ra.bioskop.repository.ScheduleRepository;
import com.ra.bioskop.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.ra.bioskop.exception.NontonFilmException.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public FilmDTO add(FilmDTO filmDTO) {
        if(filmRepository
                .findById(filmDTO.getFilmCode())
                .isEmpty()) {
            Film filmModel = new Film();
            filmModel.setFilmCode(filmDTO.getFilmCode());
            filmModel.setTitle(filmDTO.getTitle());
            filmModel.setCreatedAt(new Date());
            filmModel.setOverview(filmDTO.getOverview());
            filmModel.setRuntime(filmDTO.getRuntime());
            filmModel.setOnShow(filmDTO.isOnShow());
            filmModel.setReleaseDate(filmDTO.getReleaseDate());
            filmModel.setGenres(filmDTO.getGenres());
            return FilmMapper.toDto(filmRepository.save(filmModel));
        }
        throw throwException(ExceptionType.DUPLICATE_ENTITY, HttpStatus.CONFLICT, "Film sudah ada.");
    }

    @Override
    public FilmDTO updateName(String filmId, String newName) {
        Optional<Film> film = filmRepository.findById(filmId);
        if(film.isPresent()) {
            Film filmModel = new Film();
            filmModel.setFilmCode(film.get().getFilmCode());
            filmModel.setRuntime(film.get().getRuntime());
            filmModel.setOnShow(film.get().isOnShow());
            filmModel.setReleaseDate(film.get().getReleaseDate());
            filmModel.setCreatedAt(film.get().getCreatedAt());
            filmModel.setUpdatedAt(new Date());
            filmModel.setTitle(newName);
            filmRepository.save(filmModel);
            return FilmMapper.toDto(filmModel);
        }
        throw throwException(ExceptionType.FILM_NOT_FOUND, HttpStatus.NO_CONTENT, "Film tidak ada");
    }

    @Override
    public FilmDTO delete(String filmCode) {
        Optional<Film> film = filmRepository.findById(filmCode);
        if(film.isPresent()) {
            FilmDTO filmDTO = FilmMapper.toDto(film.get());
            filmRepository.delete(film.get());
            return filmDTO;
        }
        throw throwException(ExceptionType.FILM_NOT_FOUND, HttpStatus.NO_CONTENT, "Film tidak ada");
    }

    @Override
    public List<FilmDTO> nowPlaying() {
        List<Film> films = filmRepository.findAll();
        if(!films.isEmpty()) {
            return films.stream()
                    .filter(Film::isOnShow)
                    .map(FilmMapper::toDto)
                    .collect(Collectors.toList());
        }
        throw throwException(ExceptionType.FILM_NOT_FOUND, HttpStatus.NO_CONTENT, "Tidak ada tayangan film");
    }

    @Override
    public List<FilmDTO> addAll(List<Film> films) {
        return filmRepository.saveAll(films)
                .stream()
                .map(FilmMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FilmDTO detailFilm(String id) {
        Optional<Film> film = filmRepository.findById(id);
        if(film.isPresent()) {
            return FilmMapper.toDto(film.get());
        }
        throw throwException(ExceptionType.FILM_NOT_FOUND, HttpStatus.NO_CONTENT, "Film tidak ada");
    }

    @Override
    public List<FilmDTO> findAll() {
        List<Film> films = filmRepository.findAll();
        if(!films.isEmpty()) {
            return films.stream()
                    .map(FilmMapper::toDto)
                    .collect(Collectors.toList());
        }
        throw throwException(ExceptionType.FILM_NOT_FOUND, HttpStatus.NO_CONTENT, "Film tidak ada");
    }

    @Override
    public void addSchedule(String filmId, ScheduleDTO scheduleDTO) {
        Optional<Film> film = filmRepository.findById(filmId);
        if(film.isPresent()) {
            Film filmModel = film.get();
            Schedule schedule = new Schedule();
            schedule.setId(getScheduleId(filmModel.getFilmCode()));
            schedule.setFilm(filmModel);
            schedule.setShowAt(scheduleDTO.getShowAt());
            schedule.setStartTime(scheduleDTO.getStartTime());
            schedule.setPrice(scheduleDTO.getPrice());
            schedule.setCreatedAt(new Date());
            schedule.setUpdatedAt(new Date());

            scheduleRepository.save(schedule);
        }
        throw throwException(ExceptionType.FILM_NOT_FOUND, HttpStatus.NOT_FOUND, "film tidak ada");
    }

    private String getScheduleId(String filmCode) {
        String[] codes = Constants.randomIdentifier(filmCode);
        StringBuilder scheduleId = new StringBuilder();
        return scheduleId
                .append("sc-")
                .append(codes[4])
                .toString();
    }
}
