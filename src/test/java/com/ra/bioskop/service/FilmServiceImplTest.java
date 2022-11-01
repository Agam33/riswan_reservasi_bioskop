package com.ra.bioskop.service;

import com.ra.bioskop.exception.BioskopException;
import com.ra.bioskop.model.film.Film;
import com.ra.bioskop.repository.FilmRepository;
import com.ra.bioskop.repository.StudioRepository;
import com.ra.bioskop.util.DataDummyFilm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FilmServiceImplTest {

    @Mock
    private FilmRepository filmRepo;

    @Mock
    private StudioRepository studioRepo;

    @InjectMocks
    private FilmServiceImpl filmService;

    private DataDummyFilm dataDummyFilm;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        dataDummyFilm = new DataDummyFilm();
    }


    @Test
    @DisplayName("Get All Film, Positive")
    public void testPositiveGetAllFilm() {
        List<Film> films = dataDummyFilm.getAllFilm();
        Mockito.when(filmRepo.findAll())
                .thenReturn(films);

        var actualValue = filmService.getAllFilm();
        var expectedValue = dataDummyFilm.getAllFilm();

        Assertions.assertNotNull(actualValue);
        Assertions.assertNotNull(expectedValue);
        Assertions.assertEquals(expectedValue.size(), actualValue.size());
        Assertions.assertEquals(expectedValue.get(0).getFilmCode(), actualValue.get(0).getFilmCode());
    }

    @Test
    @DisplayName("Get All Film, " +
            "Should throw exception with status 404, Negative")
    public void testNegativeGetAllFilm() {
        BioskopException.FilmNotFoundException e = Assertions.assertThrows(BioskopException.FilmNotFoundException.class, () -> {
             Mockito.when(filmRepo.findAll()).thenReturn(new ArrayList<>());
             filmService.getAllFilm();
        });

        Assertions.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Get Detail Film by id, Positive")
    public void testPositiveGetDetailFilmById() {
        String filmId = "film-003";
        Optional<Film> film = dataDummyFilm.getFilmById(filmId);

        Mockito.when(filmRepo.findById(filmId))
                .thenReturn(film);

        var actualValue = filmService.detailFilm(filmId);
        var expectedValue = film.orElse(null);

        Assertions.assertNotNull(expectedValue);
        Assertions.assertNotNull(actualValue);
        Assertions.assertEquals(actualValue.getFilmCode(), expectedValue.getFilmCode());
        Assertions.assertEquals(actualValue.getTitle(), expectedValue.getTitle());
    }

    @Test
    @DisplayName("Get Detail Film by id, should throw exception ,Negative")
    public void testNegativeGetDetailFilmById() {
        BioskopException.FilmNotFoundException e = Assertions.assertThrows(BioskopException.FilmNotFoundException.class, () -> {
            String filmId = "film-1000";
            Optional<Film> film = dataDummyFilm.getFilmById(filmId);
            Mockito.when(filmRepo.findById(filmId))
                    .thenReturn(film);
            filmService.detailFilm(filmId);
        });
        Assertions.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Add Film, Positive")
    public void testPositiveAddFilm() {

    }

    @Test
    @DisplayName("Add film, should throw exception, Negative")
    public void testNegativeAddFilm() {

    }

}