package com.ra.bioskop.service;

import com.ra.bioskop.dto.model.film.DetailScheduleDTO;
import com.ra.bioskop.exception.BioskopException;
import com.ra.bioskop.exception.ExceptionType;
import com.ra.bioskop.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<DetailScheduleDTO> getScheduleByDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<DetailScheduleDTO> schedule = scheduleRepository.findByShowAt(localDate);
        if(!schedule.isEmpty()) {
            return schedule;
        }
        throw BioskopException.throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND, "Tidak ada jadwal film hari ini.");
    }
}
