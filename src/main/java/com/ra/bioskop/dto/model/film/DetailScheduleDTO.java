package com.ra.bioskop.dto.model.film;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailScheduleDTO {
    private String filmName;
    private String studioName;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private BigDecimal price;
}
