package com.ra.nontonfilm.dto.mapper;

import com.ra.nontonfilm.dto.model.film.FilmDTO;
import com.ra.nontonfilm.model.film.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {

    public static FilmDTO toDto(Film film) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setCode(film.getCode());
        filmDTO.setTitle(film.getTitle());
        filmDTO.setRuntime(film.getRuntime());
        filmDTO.setOnShow(film.isOnShow());
        filmDTO.setGenres(film.getGenres());
        filmDTO.setReleaseDate(film.getReleaseDate());
        return filmDTO;
    }
}
