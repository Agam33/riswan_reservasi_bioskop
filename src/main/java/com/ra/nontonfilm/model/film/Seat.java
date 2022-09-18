package com.ra.nontonfilm.model.film;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity(name = "seat")
public class Seat {

    @Id
    @Column(name = "seat_id")
    private Integer id;

    @Column(name = "seat_row")
    private String seatRow;
}
