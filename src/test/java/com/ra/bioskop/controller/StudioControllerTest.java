package com.ra.bioskop.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ra.bioskop.dto.mapper.StudioMapper;
import com.ra.bioskop.dto.model.studio.StudioDTO;
import com.ra.bioskop.dto.response.Response;
import com.ra.bioskop.service.StudioService;
import com.ra.bioskop.util.Constants;
import com.ra.bioskop.util.DataDummyStudio;
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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class StudioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudioService studioService;

    @InjectMocks
    private StudioController studioController;

    private ObjectMapper objMapper;

    private DataDummyStudio dummyStudio;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studioController)
                .build();
        objMapper = new ObjectMapper();
        dummyStudio = new DataDummyStudio();
    }

    @Test
    @DisplayName("Get All Studio, Positive")
    void testPositiveGetAllStudio() throws Exception {
        List<StudioDTO> studios = dummyStudio
                .getAllStudio()
                .stream()
                .map(StudioMapper::entityToDto)
                .toList();

        Mockito.when(studioService.getAllStudio())
                .thenReturn(studios);

        MockHttpServletResponse response = mockMvc.perform(
                get(Constants.STUDIO_V1_ENDPOINT))
                .andReturn().getResponse();

        Response successResponse = objMapper
                .readValue(response.getContentAsString(), Response.class);


        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertNotNull(successResponse.getData());
    }

    @Test
    @DisplayName("/addAll, Add all Studio, Positive")
    void testPositiveAddStudio() throws Exception {
        List<StudioDTO> studios = dummyStudio
                .getAllStudio()
                .stream()
                .map(StudioMapper::entityToDto)
                .toList();

        Mockito.doNothing()
                .when(studioService).addStudios(studios);
        studioService.addStudios(studios);
        Mockito.verify(studioService).addStudios(studios);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        objMapper.writeValue(out, studios);

        MockHttpServletResponse response = mockMvc.perform(
                post(Constants.STUDIO_V1_ENDPOINT + "/addAll")
                        .content(out.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        Assertions.assertNotNull(response.getContentAsString());
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}