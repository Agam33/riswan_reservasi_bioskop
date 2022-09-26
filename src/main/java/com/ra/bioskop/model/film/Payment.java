package com.ra.bioskop.model.film;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Payment {

    @Id
    private String id;

    private boolean status;

    private BigDecimal amount;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Booking booking;

}
