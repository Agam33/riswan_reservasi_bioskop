package com.ra.nontonfilm.model.film;

import javax.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seat_id")
    private Integer id;

    @Column(name = "booked")
    private boolean isBooked;

//    @OneToMany(mappedBy = "seats")
//    private Studio studio;

}
