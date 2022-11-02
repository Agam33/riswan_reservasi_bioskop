package com.ra.bioskop.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ra.bioskop.dto.mapper.FilmMapper;
import com.ra.bioskop.dto.model.film.FilmDTO;
import com.ra.bioskop.dto.request.film.FilmRequest;
import com.ra.bioskop.model.film.Film;
import com.ra.bioskop.repository.GenreRepository;
import com.ra.bioskop.service.FilmService;
import com.ra.bioskop.util.Constants;
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
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class FilmControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private FilmController filmController;

    @Mock
    private FilmService filmService;

    @Mock
    private GenreRepository genreRepo;

    private ObjectMapper objMapper;

    private Jackson2ObjectMapperBuilder jacksonMapper;

    private DataDummyFilm dataDummyFilm;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(filmController)
                .build();
        jacksonMapper = new Jackson2ObjectMapperBuilder();
        objMapper = jacksonMapper.build();
        dataDummyFilm = new DataDummyFilm();
    }

    @Test
    @DisplayName("Get All Film, Positive")
    void testPositiveGetAllFilm() throws Exception {
        List<FilmDTO> filmList = dataDummyFilm.getAllFilm()
                .stream().map(FilmMapper::toDto).toList();

        Mockito.when(filmService.getAllFilm())
                .thenReturn(filmList);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        objMapper.writeValue(out, filmList);

        MockHttpServletResponse response =
                mockMvc.perform(
                        get(Constants.FILM_V1_ENDPOINT)
                                .content(out.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();
        Map<String, Object> data = objMapper.readValue(response.getContentAsString(), new TypeReference<>() {});

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals("success", data.get("message"));
        Assertions.assertNotNull(data.get("data"));
    }

    @Test
    @DisplayName("/detail, Get Detail Film, Positive")
    void testPositiveGetDetailFilm() throws Exception {
        String filmCode = "film-001";
        FilmDTO filmDTO = FilmMapper.toDto(
                Objects.requireNonNull(dataDummyFilm.getFilmById(filmCode)
                        .orElse(null)));

        Mockito.when(filmService.detailFilm(filmCode))
                .thenReturn(filmDTO);

        String filmAsString = objMapper.writeValueAsString(filmDTO);
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("id", filmCode);

        MockHttpServletResponse response =
                mockMvc.perform(
                        get(Constants.FILM_V1_ENDPOINT + "/detail")
                                .params(paramsMap)
                                .content(filmAsString)
                                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();

        JsonNode jsonNode = objMapper.readTree(response.getContentAsString());

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals("success", jsonNode.get("message").asText());
    }

    @Test
    @DisplayName("/delete, Delete Film by Id, Positive")
    void testPositiveDeleteFilmById() throws Exception {
        String filmCode = "film-001";
        FilmDTO filmDTO = FilmMapper.toDto(
                Objects.requireNonNull(dataDummyFilm.getFilmById(filmCode)
                        .orElse(null)));

        Mockito.when(filmService.detailFilm(filmCode))
                .thenReturn(filmDTO);

        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("id", filmCode);

        MockHttpServletResponse response =
                mockMvc.perform(
                        delete(Constants.FILM_V1_ENDPOINT + "/delete")
                                .params(paramsMap)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();

        JsonNode jsonNode = objMapper.readTree(response.getContentAsString());

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals("success", jsonNode.get("message").asText());
    }

    @Test
    @DisplayName("/addAll, Add All Film, Positive")
    void testPositiveAddAllFilm() throws Exception {
        List<FilmDTO> listFilm = dataDummyFilm.getAllFilm()
                .stream().map(FilmMapper::toDto)
                .toList();

        List<FilmRequest> filmRequests = dataDummyFilm.getAllFilm()
                        .stream().map(this::toFilmRequest)
                        .toList();

        Mockito.when(filmService.addAll(dataDummyFilm.getAllFilm()))
                .thenReturn(listFilm);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        objMapper.writeValue(out, filmRequests);

        MockHttpServletResponse response =
                mockMvc.perform(
                        post(Constants.FILM_V1_ENDPOINT + "/addAll")
                                .content(out.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();

        JsonNode jsonNode = objMapper.readTree(response.getContentAsString());

        Assertions.assertNotNull(response.getContentAsString());
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals("success", jsonNode.get("message").asText());
    }

    @Test
    @DisplayName("/nowPlaying, Now Playing, Positive")
    void testPositiveNowPlaying() throws Exception {
        List<FilmDTO> filmList = dataDummyFilm.getAllFilm()
                .stream().filter(Film::isOnShow)
                .map(FilmMapper::toDto).toList();

        Mockito.when(filmService.nowPlaying())
                .thenReturn(filmList);

        MockHttpServletResponse response =
                mockMvc.perform(
                        get(Constants.FILM_V1_ENDPOINT + "/nowPlaying")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();

        Assertions.assertNotNull(response.getContentAsString());
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    private FilmRequest toFilmRequest(Film film) {
        FilmRequest fr = new FilmRequest();
        fr.setTitle(film.getTitle());
        fr.setOnShow(film.isOnShow());
        fr.setOverview(film.getOverview());
        fr.setRuntime(film.getRuntime());
        fr.setGenre(0);
        fr.setReleaseDate("2020-10-20");
        return fr;
    }

}