package com.ra.bioskop.dto.model.film;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleDTO {
    private String filmId;
    @JsonFormat(pattern = "HH:mm")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date showAt;
    private BigDecimal price;
}
