package com.ra.bioskop.controller;

import com.ra.bioskop.dto.mapper.FilmMapper;
import com.ra.bioskop.dto.model.film.FilmDTO;
import com.ra.bioskop.dto.model.film.ScheduleDTO;
import com.ra.bioskop.dto.request.film.FilmRequest;
import com.ra.bioskop.dto.request.film.FilmUpdateRequest;
import com.ra.bioskop.dto.request.film.ScheduleRequest;
import com.ra.bioskop.dto.response.Response;
import com.ra.bioskop.dto.response.ResponseError;
import com.ra.bioskop.model.film.Film;
import com.ra.bioskop.model.film.Genre;
import com.ra.bioskop.repository.GenreRepository;
import com.ra.bioskop.repository.StudioRepository;
import com.ra.bioskop.service.FilmService;
import com.ra.bioskop.util.Constants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.ra.bioskop.exception.NontonFilmException.*;

@RestController
@RequestMapping("/api/v1/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private StudioRepository studioRepository;

    @GetMapping("")
    public ResponseEntity<?> getAllFilm() {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.ACCEPTED.value(), new Date(),
                    "success",
                    filmService.getAllFilm()));
        } catch (FilmNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()));
        }
    }

    @GetMapping("/detail{id}/schedule")
    public ResponseEntity<?> getDetailFilmAndSchedule(
            @RequestParam(value = "id") String id) {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.ACCEPTED.value(), new Date(),
                    "success", filmService.getDetailFilmAndSchedule(id)));
        } catch (FilmNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()));
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getDetailFilm(@RequestParam(value = "id") String id) {
        try {
             return ResponseEntity.ok(new Response<>(HttpStatus.ACCEPTED.value(), new Date(),
                     "success",
                     filmService.detailFilm(id)));
        } catch (FilmNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()));
        }
    }

    @PostMapping("/addAll")
    public ResponseEntity<?> addAllFilm(@RequestBody List<FilmRequest> filmRequests) {
        try {
            List<Film> filmModels = filmRequests.stream()
                    .map(this::filmRequestToDto)
                    .map(FilmMapper::toModel)
                    .collect(Collectors.toList());
            filmService.addAll(filmModels);
            return ResponseEntity.ok(new Response<>(HttpStatus.ACCEPTED.value(), new Date(),
                    "success",
                    null));
        } catch (DuplicateEntityException e) {
            return ResponseEntity.ok(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFilm(@RequestBody FilmRequest filmRequest) {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.ACCEPTED.value(), new Date(),
                    "success",
                    filmService.add(filmRequestToDto(filmRequest))));
        } catch (FilmNotFoundException
                 | DuplicateEntityException e) {
            return ResponseEntity.ok(new ResponseError(HttpStatus.NO_CONTENT.value(), new Date(), e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateFilmName(@RequestBody FilmUpdateRequest filmUpdateRequest) {
        try {
             return ResponseEntity.ok(new Response<>(HttpStatus.ACCEPTED.value(), new Date(),
                     "success",
                     filmService.updateName(filmUpdateRequest.getCode(), filmUpdateRequest.getNewName())));
        } catch (FilmNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFilm(@RequestParam(value = "id") String id) {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.ACCEPTED.value(), new Date(),
                    "success",
                    filmService.delete(id)));
        } catch (FilmNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()));
        }
    }

    @GetMapping("/nowPlaying")
    public ResponseEntity<?> nowPlaying() {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.ACCEPTED.value(), new Date(),
                    "success", filmService.nowPlaying()));
        } catch (FilmNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()));
        }
    }

    @PostMapping("/addSchedule")
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.ACCEPTED.value(), new Date(),
                    "success",
                    filmService.addSchedule(scheduleRequestToDTO(scheduleRequest))));
        } catch (FilmNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(HttpStatus.NOT_ACCEPTABLE.value(), new Date(), e.getMessage()));
        }
    }

    private FilmDTO filmRequestToDto(FilmRequest filmRequest) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setFilmCode(getFilmCode(filmRequest.getTitle()));
        filmDTO.setTitle(filmRequest.getTitle());
        filmDTO.setOverview(filmRequest.getOverview());
        filmDTO.setRuntime(filmRequest.getRuntime());
        filmDTO.setOnShow(filmRequest.isOnShow());
        filmDTO.setReleaseDate(filmRequest.getReleaseDate());

        Integer genrePos = filmRequest.getGenre();
        Genre genre = genreRepository
                .findById(genrePos)
                .orElse(null);

        filmDTO.getGenres().add(genre);
        return filmDTO;
    }

    private String getFilmCode(String filmTitle) {
        String[] codes = Constants.randomIdentifier(filmTitle);
        StringBuilder filmCode = new StringBuilder();
        return filmCode.append("film")
                .append("-")
                .append(codes[1])
                .toString();
    }

    private ScheduleDTO scheduleRequestToDTO(ScheduleRequest scheduleRequest) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setFilmId(scheduleRequest.getFilmId());
        scheduleDTO.setShowAt(scheduleRequest.getShowAt());
        scheduleDTO.setStartTime(scheduleRequest.getStartTime());
        scheduleDTO.setPrice(scheduleRequest.getPrice());
        return scheduleDTO;
    }
}