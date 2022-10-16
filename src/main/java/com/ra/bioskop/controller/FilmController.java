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
import com.ra.bioskop.service.FilmService;
import com.ra.bioskop.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.ra.bioskop.exception.BioskopException.*;

@Tag(name = "Film")
@RestController
@RequestMapping("/api/v1/films")
public class FilmController {

    @Autowired
    private FilmService filmService;
    @Autowired
    private GenreRepository genreRepository;

    @Operation(summary = "Mengambil semua data film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Tidak ada film.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class ))}),
            @ApiResponse(responseCode = "200", description = "Berhasil menambahkan.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class ))})})
    @GetMapping
    public ResponseEntity<?> getAllFilm() {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    "success",
                    filmService.getAllFilm()));
        } catch (FilmNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()), e.getStatusCode());
        }
    }

    @Operation(summary = "Mengambil schedule dari film tertentu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Tidak ada film.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class ))}),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class ))})})
    @GetMapping("/detail/schedule")
    public ResponseEntity<?> getDetailFilmAndSchedule(
            @RequestParam(value = "id") String id) {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    "success", filmService.getFilmSchedule(id)));
        } catch (FilmNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()), e.getStatusCode());
        }
    }

    @Operation(summary = "Mengambil detail dari film tertentu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Tidak ada film.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class ))}),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class ))})})
    @GetMapping("/detail")
    public ResponseEntity<?> getDetailFilm(@RequestParam(value = "id") String id) {
        try {
             return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                     "success",
                     filmService.detailFilm(id)));
        } catch (FilmNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()), e.getStatusCode());
        }
    }

    @Operation(summary = "Mengambil detail dari film tertentu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "406", description = "Tidak diterima.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class ))}),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class ))})})
    @PostMapping("/addAll")
    public ResponseEntity<?> addAllFilm(@RequestBody List<FilmRequest> filmRequests) {
        try {
            List<Film> filmModels = filmRequests.stream()
                    .map(this::filmRequestToDto)
                    .map(FilmMapper::toModel)
                    .collect(Collectors.toList());
            filmService.addAll(filmModels);
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    "success",
                    null));
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()), e.getStatusCode());
        }
    }

    @Operation(summary = "Menambahkan film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Film sudah ada.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class ))}),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class ))})})
    @PostMapping("/add")
    public ResponseEntity<?> addFilm(@RequestBody FilmRequest filmRequest) {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    "success",
                    filmService.add(filmRequestToDto(filmRequest))));
        } catch (DuplicateEntityException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()), e.getStatusCode());
        }
    }

    @Operation(summary = "Memperbarui film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = "Film sudah ada.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class ))}),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class ))})})
    @PostMapping("/update")
    public ResponseEntity<?> updateFilmName(@RequestBody FilmUpdateRequest filmUpdateRequest) {
        try {
             return ResponseEntity.ok(new Response<>(HttpStatus.ACCEPTED.value(), new Date(),
                     "success",
                     filmService.updateName(filmUpdateRequest.getFilmCode(), filmUpdateRequest.getNewName())));
        } catch (FilmNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()), e.getStatusCode());
        }
    }

    @Operation(summary = "Menghapus film berdasarkan id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Film tidak ada.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class ))}),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class ))})})
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFilm(@RequestParam(value = "id") String id) {
        try {
            filmService.delete(id);
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    "success", null));
        } catch (FilmNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()), e.getStatusCode());
        }
    }

    @Operation(summary = "Mengambil daftar film yang sedang tayang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Film tidak ada.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class ))}),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class ))})})
    @GetMapping("/nowPlaying")
    public ResponseEntity<?> nowPlaying() {
        try {
            return ResponseEntity.ok(new Response<>(HttpStatus.ACCEPTED.value(), new Date(),
                    "success", filmService.nowPlaying()));
        } catch (FilmNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()), e.getStatusCode());
        }
    }

    @Operation(summary = "Menambahkan jadwal film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Film tidak ada.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseError.class ))}),
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class ))})})
    @PostMapping("/addSchedule")
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        try {
            filmService.addSchedule(scheduleRequestToDTO(scheduleRequest));
            return ResponseEntity.ok(new Response<>(HttpStatus.OK.value(), new Date(),
                    "success", null));
        } catch (FilmNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()), e.getStatusCode());
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(new ResponseError(e.getStatusCode().value(), new Date(), e.getMessage()), e.getStatusCode());
        }
    }

    private FilmDTO filmRequestToDto(FilmRequest filmRequest) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setFilmCode(getFilmCode(filmRequest.getTitle()));
        filmDTO.setTitle(filmRequest.getTitle());
        filmDTO.setOverview(filmRequest.getOverview());
        filmDTO.setRuntime(filmRequest.getRuntime());
        filmDTO.setOnShow(filmRequest.isOnShow());
        filmDTO.setReleaseDate(LocalDate.parse(filmRequest.getReleaseDate()));

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
        scheduleDTO.setFilmId(scheduleRequest.getFilmCode());
        scheduleDTO.setStudioId(scheduleRequest.getStudioId());
        scheduleDTO.setShowAt(LocalDate.parse(scheduleRequest.getShowAt()));
        scheduleDTO.setStartTime(LocalTime.parse(scheduleRequest.getStartTime()));
        scheduleDTO.setEndTime(LocalTime.parse(scheduleRequest.getEndTime()));
        scheduleDTO.setPrice(scheduleRequest.getPrice());
        return scheduleDTO;
    }
}
