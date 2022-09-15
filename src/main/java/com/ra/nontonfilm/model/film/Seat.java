package com.ra.nontonfilm.model.film;

import javax.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "seat_id")
    private String id;

    @ManyToOne(targetEntity = Studio.class)
    @JoinColumn(name = "studio_name")
    private Studio studio;

}
