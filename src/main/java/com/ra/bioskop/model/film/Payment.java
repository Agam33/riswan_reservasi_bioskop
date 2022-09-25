package com.ra.bioskop.model.film;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Payment {

    @Id
    private String id;

    private BigDecimal amount;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Booking booking;

}
