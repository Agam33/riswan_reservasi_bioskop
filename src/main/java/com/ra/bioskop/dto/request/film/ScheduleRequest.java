package com.ra.bioskop.dto.request.film;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {
    private Date startTime;
    private Date endTime;
    private Date showAt;
    private BigDecimal price;
}
