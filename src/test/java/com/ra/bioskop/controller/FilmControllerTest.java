package com.ra.bioskop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ra.bioskop.repository.GenreRepository;
import com.ra.bioskop.service.FilmService;
import com.ra.bioskop.util.DataDummyFilm;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private FilmController filmController;

    @Mock
    private FilmService filmService;

    @Mock
    private GenreRepository genreRepo;

    private ObjectMapper objMapper;

    private DataDummyFilm dataDummyFilm;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(filmController)
                .build();
        objMapper = new ObjectMapper();
        dataDummyFilm = new DataDummyFilm();
    }

    @Test
    @DisplayName("Get All Film, Positive")
    void testPositiveGetAllFilm() {
        
    }
}