package com.ra.bioskop.service;

import com.ra.bioskop.dto.model.film.DetailScheduleDTO;
import com.ra.bioskop.dto.model.film.ScheduleDTO;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    List<DetailScheduleDTO> getScheduleByDate(String date);
}
