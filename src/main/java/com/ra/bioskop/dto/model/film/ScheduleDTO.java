package com.ra.bioskop.dto.model.film;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleDTO {
    private String filmId;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate showAt;
    private BigDecimal price;
}
