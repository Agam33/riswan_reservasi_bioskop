package com.ra.bioskop.service;

import com.ra.bioskop.dto.model.film.FilmDTO;
import com.ra.bioskop.dto.model.film.ScheduleDTO;
import com.ra.bioskop.model.film.Film;

import java.util.List;

public interface FilmService {
    FilmDTO add(FilmDTO filmDTO);
    FilmDTO updateName(String filmId, String newName);
    FilmDTO delete(String filmCode);
    List<FilmDTO> nowPlaying();
    List<FilmDTO> addAll(List<Film> films);
    FilmDTO detailFilm(String id);
    List<FilmDTO> findAll();
    void addSchedule(String filmId, ScheduleDTO scheduleDTO);
}
