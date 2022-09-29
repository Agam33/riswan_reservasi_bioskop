package com.ra.bioskop.dto.request.film;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {
    @NotNull
    private String filmCode;

    @NotNull
    private Integer studioId;

    @JsonFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime endTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate showAt;

    @NotNull
    private BigDecimal price;
}
