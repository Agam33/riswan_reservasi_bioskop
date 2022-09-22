package com.ra.nontonfilm.dto.mapper;

import com.ra.nontonfilm.dto.model.film.FilmDTO;
import com.ra.nontonfilm.model.film.Film;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FilmMapper {

    public static FilmDTO toDto(Film film) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setFilmCode(film.getFilmCode());
        filmDTO.setTitle(film.getTitle());
        filmDTO.setOverview(film.getOverview());
        filmDTO.setRuntime(film.getRuntime());
        filmDTO.setOnShow(film.isOnShow());
        filmDTO.setGenres(film.getGenres());
        filmDTO.setReleaseDate(film.getReleaseDate());
        return filmDTO;
    }

    public static Film toModel(FilmDTO filmDTO) {
        Film filmModel = new Film();
        filmModel.setFilmCode(filmDTO.getFilmCode());
        filmModel.setUpdatedAt(new Date());
        filmModel.setCreatedAt(new Date());
        filmModel.setOverview(filmDTO.getOverview());
        filmModel.setReleaseDate(filmDTO.getReleaseDate());
        filmModel.setOnShow(filmDTO.isOnShow());
        filmModel.setGenres(filmDTO.getGenres());
        filmModel.setTitle(filmDTO.getTitle());
        filmModel.setRuntime(filmDTO.getRuntime());
        return filmModel;
    }
}
