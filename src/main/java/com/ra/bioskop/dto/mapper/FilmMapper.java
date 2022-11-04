package com.ra.bioskop.dto.mapper;

import com.ra.bioskop.dto.model.film.FilmAndScheduleDTO;
import com.ra.bioskop.dto.model.film.FilmDTO;
import com.ra.bioskop.model.film.Film;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FilmMapper {
    FilmMapper() {}
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

    public static FilmAndScheduleDTO toFilmAndScheduleDTO(Film filmModel) {
        FilmAndScheduleDTO filmAndSchedule = new FilmAndScheduleDTO();
        filmAndSchedule.setFilmId(filmModel.getFilmCode());
        filmAndSchedule.setSchedules(filmModel.getSchedules());
        filmAndSchedule.setTitle(filmModel.getTitle());
        return filmAndSchedule;
    }

    public static Film toModel(FilmDTO filmDTO) {
        Film filmModel = new Film();
        filmModel.setFilmCode(filmDTO.getFilmCode());
        filmModel.setUpdatedAt(LocalDateTime.now());
        filmModel.setCreatedAt(LocalDateTime.now());
        filmModel.setOverview(filmDTO.getOverview());
        filmModel.setReleaseDate(filmDTO.getReleaseDate());
        filmModel.setOnShow(filmDTO.isOnShow());
        filmModel.setGenres(filmDTO.getGenres());
        filmModel.setTitle(filmDTO.getTitle());
        filmModel.setRuntime(filmDTO.getRuntime());
        return filmModel;
    }
}
