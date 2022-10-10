package com.ra.bioskop.service;

import com.ra.bioskop.dto.mapper.FilmMapper;
import com.ra.bioskop.dto.model.film.FilmAndScheduleDTO;
import com.ra.bioskop.dto.model.film.FilmDTO;
import com.ra.bioskop.dto.model.film.ScheduleDTO;
import com.ra.bioskop.exception.ExceptionType;
import com.ra.bioskop.model.film.Film;
import com.ra.bioskop.model.film.Schedule;
import com.ra.bioskop.model.film.Studio;
import com.ra.bioskop.repository.FilmRepository;
import com.ra.bioskop.repository.ScheduleRepository;
import com.ra.bioskop.repository.StudioRepository;
import com.ra.bioskop.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.ra.bioskop.exception.BioskopException.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private StudioRepository studioRepository;

    @Override
    public FilmDTO add(FilmDTO filmDTO) {
        if(filmRepository
                .findById(filmDTO.getFilmCode())
                .isEmpty()) {
            Film filmModel = new Film();
            filmModel.setFilmCode(filmDTO.getFilmCode());
            filmModel.setTitle(filmDTO.getTitle());
            filmModel.setCreatedAt(LocalDateTime.now());
            filmModel.setOverview(filmDTO.getOverview());
            filmModel.setRuntime(filmDTO.getRuntime());
            filmModel.setOnShow(filmDTO.isOnShow());
            filmModel.setReleaseDate(filmDTO.getReleaseDate());
            filmModel.setGenres(filmDTO.getGenres());
            return FilmMapper.toDto(filmRepository.save(filmModel));
        }
        throw throwException(ExceptionType.DUPLICATE_ENTITY, HttpStatus.CONFLICT, "film sudah ada.");
    }

    @Override
    public FilmDTO updateName(String filmId, String newName) {
        Optional<Film> film = filmRepository.findById(filmId);
        if(film.isPresent()) {
            Film filmModel = film.get();
            filmModel.setUpdatedAt(LocalDateTime.now());
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
    public List<FilmDTO> getAllFilm() {
        List<Film> films = filmRepository.findAll();
        if(!films.isEmpty()) {
            return films.stream()
                    .map(FilmMapper::toDto)
                    .collect(Collectors.toList());
        }
        throw throwException(ExceptionType.FILM_NOT_FOUND, HttpStatus.NOT_FOUND, "Film tidak ada");
    }

    @Override
    public ScheduleDTO addSchedule(ScheduleDTO scheduleDTO) {
        Optional<Film> film = filmRepository.findById(scheduleDTO.getFilmId());
        if(film.isPresent()) {

            Film filmModel = film.get();
            Schedule schedule = new Schedule();

            schedule.setId(getScheduleId(filmModel.getFilmCode(),
                    scheduleDTO.getStartTime(),
                    scheduleDTO.getShowAt()));
            schedule.setShowAt(scheduleDTO.getShowAt());
            schedule.setStartTime(scheduleDTO.getStartTime());
            schedule.setEndTime(scheduleDTO.getEndTime());
            schedule.setPrice(scheduleDTO.getPrice());
            schedule.setCreatedAt(LocalDateTime.now());
            schedule.setUpdatedAt(LocalDateTime.now());

            Optional<Studio> studio = studioRepository.findById(scheduleDTO.getStudioId());
            if(studio.isEmpty())
                throw throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND, "studio tidak ditemukan.");

            schedule.setStudio(studio.get());
            filmModel.getSchedules().add(schedule);
            schedule.setFilm(filmModel);
            filmRepository.save(filmModel);
            return scheduleDTO;
        }
        throw throwException(ExceptionType.FILM_NOT_FOUND, HttpStatus.NOT_FOUND, "film tidak ada.");
    }

    @Override
    public FilmAndScheduleDTO getFilmSchedule(String filmId) {
        Optional<Film> film = filmRepository.findById(filmId);
        if(film.isPresent()) {
            return FilmMapper.toFilmAndScheduleDTO(film.get());
        }
        throw throwException(ExceptionType.FILM_NOT_FOUND, HttpStatus.NOT_FOUND, "film tidak ada");
    }

    private String getScheduleId(String filmCode, LocalTime startTime, LocalDate showAt) {
        String[] codes = Constants.randomIdentifier(filmCode + startTime.toString() + showAt.getDayOfWeek());
        StringBuilder scheduleId = new StringBuilder();
        return scheduleId.append("sc-").append(codes[3]).append("-").append(codes[4]).toString();
    }
}
