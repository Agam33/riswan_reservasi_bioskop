package com.ra.nontonfilm.controller;

import com.ra.nontonfilm.dto.mapper.FilmMapper;
import com.ra.nontonfilm.dto.model.film.FilmDTO;
import com.ra.nontonfilm.dto.request.film.FilmRequest;
import com.ra.nontonfilm.dto.request.film.FilmUpdateRequest;
import com.ra.nontonfilm.dto.response.Response;
import com.ra.nontonfilm.dto.response.ResponseError;
import com.ra.nontonfilm.model.film.Film;
import com.ra.nontonfilm.model.film.Genre;
import com.ra.nontonfilm.repository.GenreRepository;
import com.ra.nontonfilm.service.FilmService;
import com.ra.nontonfilm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.ra.nontonfilm.exception.NontonFilmException.*;

@RestController
@RequestMapping("/api/v1/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(new Response<>(false,
                    "success",
                    filmService.findAll()));
        } catch (FilmNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<?> detailFilm(@RequestParam(value = "id") String id) {
        try {
             return ResponseEntity.ok(new Response<>(false,
                     "success",
                     filmService.detailFilm(id)));
        } catch (FilmNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
        }
    }

    @PostMapping("/addAll")
    public ResponseEntity<?> add(@RequestBody List<FilmRequest> filmRequests) {
        try {
            List<Film> filmModels = filmRequests.stream()
                    .map(this::addFilm)
                    .map(FilmMapper::toModel)
                    .collect(Collectors.toList());
            filmService.addAll(filmModels);
            return ResponseEntity.ok(new Response<>(false,
                    "success",
                    null));
        } catch (DuplicateEntityException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody FilmRequest filmRequest) {
        try {
            return ResponseEntity.ok(new Response<>(false,
                    "success",
                    filmService.add(addFilm(filmRequest))));
        } catch (FilmNotFoundException
                 | DuplicateEntityException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateName(@RequestBody FilmUpdateRequest filmUpdateRequest) {
        try {
             return ResponseEntity.ok(new Response<>(false,
                     "success",
                     filmService.updateName(filmUpdateRequest.getCode(), filmUpdateRequest.getNewName())));
        } catch (FilmNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(value = "id") String id) {
        try {

            return ResponseEntity.ok(new Response<>(false,
                    "success",
                    null));
        } catch (FilmNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
        }
    }

    @GetMapping("/nowPlaying")
    public ResponseEntity<?> nowPlaying() {
        try {
            return ResponseEntity.ok(new Response<>(false,
                    "success", filmService.nowPlaying()));
        } catch (FilmNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
        }
    }



    private FilmDTO addFilm(FilmRequest filmRequest) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setCode(getFilmCode(filmRequest.getTitle()));
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

}
