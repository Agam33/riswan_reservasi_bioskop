package com.ra.nontonfilm.service;

import com.ra.nontonfilm.dto.model.film.FilmDTO;
import com.ra.nontonfilm.model.film.Film;

import java.util.List;

public interface FilmService {
    FilmDTO add(FilmDTO filmDTO);
    FilmDTO updateName(String filmId, String newName);
    FilmDTO delete(String filmCode);
    List<FilmDTO> nowPlaying();
    List<FilmDTO> addAll(List<Film> films);

    FilmDTO detailFilm(String id);

    List<FilmDTO> findAll();

}
