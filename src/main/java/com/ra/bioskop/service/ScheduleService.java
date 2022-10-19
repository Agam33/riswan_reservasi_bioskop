package com.ra.bioskop.service;

import java.util.List;

import com.ra.bioskop.dto.model.film.DetailScheduleDTO;

public interface ScheduleService {

    List<DetailScheduleDTO> getScheduleByDate(String date);
}
