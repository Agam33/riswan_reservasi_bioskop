package com.ra.nontonfilm.service;

import com.ra.nontonfilm.dto.model.film.FilmDTO;

public interface FilmService {
    FilmDTO add(FilmDTO filmDTO);
    FilmDTO updateName(String filmId, String newName);
    FilmDTO delete(String filmCode);

}
