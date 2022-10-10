package com.ra.bioskop.dto.request.film;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
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

    @NotNull(message = "HH:mm")
    private String startTime;

    @NotNull(message = "HH:mm")
    private String endTime;

    @NotNull(message = "yyyy-MM-dd")
//    @Schema(description = "yyyy-MM-dd", type = )
    private String showAt;

    @NotNull
    private BigDecimal price;
}
