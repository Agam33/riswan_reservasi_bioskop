package com.ra.bioskop.repository;

import com.ra.bioskop.dto.model.film.DetailScheduleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    public void testDetailSchedule() {
        LocalDate localDate = LocalDate.of(2022, 9,23);
        List<DetailScheduleDTO> detailScheduleDTO = scheduleRepository.findByShowAt(LocalDate.parse("2022-09-23"));
        System.out.println(detailScheduleDTO);
    }
}