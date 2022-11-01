package com.ra.bioskop.service;

import com.ra.bioskop.dto.model.film.DetailScheduleDTO;
import com.ra.bioskop.exception.BioskopException;
import com.ra.bioskop.repository.ScheduleRepository;
import com.ra.bioskop.util.DataDummySchedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

class ScheduleServiceImplTest {

    @Mock
    private ScheduleRepository scheduleRepo;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    private DataDummySchedule dummySchedule;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        dummySchedule = new DataDummySchedule();
    }

    @Test
    @DisplayName("Get Schedule by Date, Positive")
    public void testPositiveGetScheduleByDate() {
        String date = "2022-10-31";
        LocalDate localDate = LocalDate.parse(date);
        List<DetailScheduleDTO> detailSchedules = dummySchedule.getDetailSchedules();

        Mockito.when(scheduleRepo.findByShowAt(localDate))
                .thenReturn(detailSchedules);

        var actualValue = scheduleService.getScheduleByDate(date);

        Assertions.assertEquals(detailSchedules.size(), actualValue.size());
        Assertions.assertEquals(detailSchedules.get(0).getStudioName(), actualValue.get(0).getStudioName());
        Assertions.assertEquals(detailSchedules.get(0).getFilmName(), actualValue.get(0).getFilmName());
    }

    @Test
    @DisplayName("Get Schedule by Date, Negative")
    public void testNegativeGetScheduleByDate() {
        BioskopException.EntityNotFoundException e =
                Assertions.assertThrows(BioskopException.EntityNotFoundException.class, () -> {
                    String date = "2022-10-31";
                    scheduleService.getScheduleByDate(date);
                });
        Assertions.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
    }

}