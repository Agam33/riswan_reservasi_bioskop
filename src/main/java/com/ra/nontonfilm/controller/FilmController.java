package com.ra.nontonfilm.controller;

import com.ra.nontonfilm.dto.model.film.FilmDTO;
import com.ra.nontonfilm.dto.request.film.FilmRequest;
import com.ra.nontonfilm.dto.request.film.FilmUpdateRequest;
import com.ra.nontonfilm.dto.response.Response;
import com.ra.nontonfilm.dto.response.ResponseError;
import com.ra.nontonfilm.model.film.Genre;
import com.ra.nontonfilm.repository.GenreRepository;
import com.ra.nontonfilm.service.FilmService;
import com.ra.nontonfilm.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.ra.nontonfilm.exception.NontonFilmException.*;

@RestController
@RequestMapping("/api/v1/film")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private GenreRepository genreRepository;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody FilmRequest filmRequest) {
        try {
            return ResponseEntity.ok(new Response(false,
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
             return ResponseEntity.ok(new Response(false,
                     "success",
                     filmService.updateName(filmUpdateRequest.getCode(), filmUpdateRequest.getNewName())));
        } catch (FilmNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") String id) {
        try {
            return ResponseEntity.ok(new Response(false,
                    "success",
                    filmService.delete(id)));
        } catch (FilmNotFoundException e) {
            return ResponseEntity.ok(new ResponseError(true, new Date(), e.getMessage()));
        }
    }

    private FilmDTO addFilm(FilmRequest filmRequest) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setCode(getFilmCode(filmRequest.getTitle()));
        filmDTO.setTitle(filmRequest.getTitle());
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
