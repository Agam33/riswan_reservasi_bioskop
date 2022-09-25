package com.ra.bioskop.model.film;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "booking")
public class Booking {

    @Id
    private String id;

    private boolean status;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Payment payment;
}
