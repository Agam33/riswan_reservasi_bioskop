package com.ra.bioskop.service;

import com.ra.bioskop.dto.mapper.FilmMapper;
import com.ra.bioskop.dto.model.film.FilmDTO;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    void testPositiveGetAllFilm() {
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
    void testNegativeGetAllFilm() {
        BioskopException.FilmNotFoundException e = Assertions.assertThrows(BioskopException.FilmNotFoundException.class, () -> {
             Mockito.when(filmRepo.findAll()).thenReturn(new ArrayList<>());
             filmService.getAllFilm();
        });
        Assertions.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("Get Detail Film by id, Positive")
    void testPositiveGetDetailFilmById() {
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
    void testNegativeGetDetailFilmById() {
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
    void testPositiveAddFilm() {
        Film filmModel = dataDummyFilm.getAllFilm().get(1);

        FilmDTO filmDTO = FilmMapper.toDto(filmModel);

        Mockito.when(filmRepo.save(filmModel))
                .thenReturn(filmModel);

        var actualValue = filmService.add(filmDTO);
        var expectedValue = dataDummyFilm.getAllFilm().get(1);

        Assertions.assertNotNull(actualValue);
        Assertions.assertNotNull(expectedValue);
        Assertions.assertEquals(expectedValue.getFilmCode(), actualValue.getFilmCode());
        Assertions.assertEquals(expectedValue.getTitle(), actualValue.getTitle());
        Assertions.assertEquals(expectedValue.getOverview(), actualValue.getOverview());
        Assertions.assertEquals(expectedValue.getRuntime(), actualValue.getRuntime());
    }

    @Test
    @DisplayName("Add film, should throw exception, Negative")
    void testNegativeAddFilm() {
        BioskopException.DuplicateEntityException e =
                Assertions.assertThrows(BioskopException.DuplicateEntityException.class, () -> {
                    Film filmModel = dataDummyFilm.getAllFilm().get(1);

                    FilmDTO filmDTO = FilmMapper.toDto(filmModel);

                    Mockito.when(filmRepo.findById(filmDTO.getFilmCode()))
                                    .thenReturn(Optional.of(filmModel));
                    filmService.add(filmDTO);
                });

        Assertions.assertEquals(HttpStatus.CONFLICT, e.getStatusCode());
    }

    @Test
    @DisplayName("Now Playing, Positive")
    void testPositiveNowPlaying() {
        List<Film> films = dataDummyFilm.getAllFilm()
                .stream().filter(Film::isOnShow).toList();

        Mockito.when(filmRepo.findAll())
                .thenReturn(films);

        var actualValue = filmService.nowPlaying();

        Assertions.assertEquals(films.size(), actualValue.size());
        Assertions.assertEquals(films.get(0).getFilmCode(), actualValue.get(0).getFilmCode());
    }

    @Test
    @DisplayName("Update name, Positive")
    void testPositiveUpdateName() {
        String newTitle = "Hello World";
        String filmCode = "film-001";

        Optional<Film> film = dataDummyFilm.getFilmById(filmCode);

        Film filmModel = film.get();
        filmModel.setTitle(newTitle);
        filmModel.setUpdatedAt(LocalDateTime.now());

        Mockito.when(filmRepo.findById(filmCode))
                        .thenReturn(film);
        Mockito.when(filmRepo.save(filmModel))
                .thenReturn(filmModel);

        var actualValue = filmService.updateName(filmCode, newTitle);

        Assertions.assertNotNull(actualValue);
        Assertions.assertEquals(filmModel.getFilmCode(), actualValue.getFilmCode());
        Assertions.assertEquals(newTitle, actualValue.getTitle());
    }

    @Test
    @DisplayName("Update name, Negative")
    void testNegativeUpdateName() {
        BioskopException.FilmNotFoundException e =
                Assertions.assertThrows(BioskopException.FilmNotFoundException.class, () -> {
                    String newTitle = "Hello World";
                    String filmCode = "film-001";
                    filmService.updateName(filmCode, newTitle);
                });

        Assertions.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
    }

    @Test
    @DisplayName("Get Film Schedule, Positive")
    void testPositiveGetFilmSchedule() {
        String id = "film-001";
        Optional<Film> film = dataDummyFilm.getFilmById(id);

        Mockito.when(filmRepo.findById(id))
                .thenReturn(film);

        var actualValue = filmService.getFilmSchedule(id);
        var expectedValue = film.get();

        Assertions.assertEquals(expectedValue.getFilmCode(), actualValue.getFilmId());
        Assertions.assertEquals(expectedValue.getTitle(), actualValue.getTitle());
    }

    @Test
    @DisplayName("Get Film Schedule, Negative")
    void testNegativeGetFilmSchedule() {
        BioskopException.FilmNotFoundException e =
                Assertions.assertThrows(BioskopException.FilmNotFoundException.class, () -> {
                    String filmId = "film-001";
                    filmService.getFilmSchedule(filmId);
                });
        Assertions.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
    }

}