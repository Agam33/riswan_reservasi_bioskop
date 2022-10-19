package com.ra.bioskop.dto.request.film;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
    @Schema(description = "input: HH:mm", type = "string", example = "14:00")
    private String startTime;

    @NotNull(message = "HH:mm")
    @Schema(description = "input: HH:mm", type = "string", example = "14:00")
    private String endTime;

    @NotNull(message = "yyyy-MM-dd")
    @Schema(description = "input: yyyy-MM-dd", type = "string", example = "2022-10-10")
    private String showAt;

    @NotNull
    private BigDecimal price;
}
