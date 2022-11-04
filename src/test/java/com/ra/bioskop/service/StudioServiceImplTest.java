package com.ra.bioskop.service;

import com.ra.bioskop.model.film.Studio;
import com.ra.bioskop.repository.StudioRepository;
import com.ra.bioskop.util.DataDummyStudio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

class StudioServiceImplTest {

    @Mock
    private StudioRepository studioRepo;

    @InjectMocks
    private StudioServiceImpl studioService;

    private DataDummyStudio dummyStudio;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        dummyStudio = new DataDummyStudio();
    }

    @Test
    @DisplayName("Get All Studio, Positive")
    public void testPositiveGetAllStudio() {
        List<Studio> studios = dummyStudio.getAllStudio();

        Mockito.when(studioRepo.findAll())
                .thenReturn(studios);

        var actualValue = studioService.getAllStudio();

        Assertions.assertNotNull(actualValue);
        Assertions.assertEquals(studios.size(), actualValue.size());
        Assertions.assertEquals(studios.get(0).getMaxSeat(), actualValue.get(0).getMaxSeat());
        Assertions.assertEquals(studios.get(0).getName(), actualValue.get(0).getStudioName());
    }

}