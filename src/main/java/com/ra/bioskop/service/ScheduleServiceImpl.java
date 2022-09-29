package com.ra.bioskop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ra.bioskop.dto.model.film.DetailScheduleDTO;
import com.ra.bioskop.exception.BioskopException;
import com.ra.bioskop.exception.ExceptionType;
import com.ra.bioskop.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public List<DetailScheduleDTO> getScheduleByDate(String date) {
        ObjectMapper objectMapper = new ObjectMapper();

        LocalDate localDate = LocalDate.parse(date);
        List<DetailScheduleDTO> objDTO = scheduleRepository.findByShowAt(localDate);
        if(!objDTO.isEmpty()) {
            return objDTO;
        }
        throw BioskopException.throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND, "Tidak ada jadwal film hari ini.");
    }
}
