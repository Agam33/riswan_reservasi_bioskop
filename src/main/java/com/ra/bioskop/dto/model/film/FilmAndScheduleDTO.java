package com.ra.bioskop.dto.model.film;

import com.ra.bioskop.model.film.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FilmAndScheduleDTO {
    private String filmId;
    private String title;
    private List<Schedule> schedules;
}
