package com.ra.nontonfilm.service;

import com.ra.nontonfilm.dto.mapper.FilmMapper;
import com.ra.nontonfilm.dto.model.film.FilmDTO;
import com.ra.nontonfilm.exception.ExceptionType;
import com.ra.nontonfilm.model.film.Film;
import com.ra.nontonfilm.repository.FilmRepository;
import com.ra.nontonfilm.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ra.nontonfilm.exception.NontonFilmException.*;

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
                .findById(filmDTO.getCode())
                .isEmpty()) {
            Film filmModel = new Film();
            filmModel.setCode(filmDTO.getCode());
            filmModel.setTitle(filmDTO.getTitle());
            filmModel.setCreatedAt(new Date());
            filmModel.setOverview(filmDTO.getOverview());
            filmModel.setRuntime(filmDTO.getRuntime());
            filmModel.setOnShow(filmDTO.isOnShow());
            filmModel.setReleaseDate(filmDTO.getReleaseDate());
            filmModel.setGenres(filmDTO.getGenres());
            return FilmMapper.toDto(filmRepository.save(filmModel));
        }
        throw throwException(ExceptionType.DUPLICATE_ENTITY, "Film sudah ada.");
    }

    @Override
    public FilmDTO updateName(String filmId, String newName) {
        Optional<Film> film = filmRepository.findById(filmId);
        if(film.isPresent()) {
            Film filmModel = new Film();
            filmModel.setCode(film.get().getCode());
            filmModel.setRuntime(film.get().getRuntime());
            filmModel.setOnShow(film.get().isOnShow());
            filmModel.setReleaseDate(film.get().getReleaseDate());
            filmModel.setCreatedAt(film.get().getCreatedAt());
            filmModel.setUpdatedAt(new Date());
            filmModel.setTitle(newName);
            filmRepository.save(filmModel);
            return FilmMapper.toDto(filmModel);
        }
        throw throwException(ExceptionType.FILM_NOT_FOUND, "Film tidak ada");
    }

    @Override
    public FilmDTO delete(String filmCode) {
        Optional<Film> film = filmRepository.findById(filmCode);
        if(film.isPresent()) {
            FilmDTO filmDTO = FilmMapper.toDto(film.get());
            filmRepository.delete(film.get());
            return filmDTO;
        }
        throw throwException(ExceptionType.FILM_NOT_FOUND, "Film tidak ada");
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
        throw throwException(ExceptionType.FILM_NOT_FOUND, "Tidak ada tayangan film");
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
        throw throwException(ExceptionType.FILM_NOT_FOUND, "Film tidak ada");
    }

    @Override
    public List<FilmDTO> findAll() {
        List<Film> films = filmRepository.findAll();
        if(!films.isEmpty()) {
            return films.stream()
                    .map(FilmMapper::toDto)
                    .collect(Collectors.toList());
        }
        throw throwException(ExceptionType.FILM_NOT_FOUND, "Film tidak ada");
    }


}
