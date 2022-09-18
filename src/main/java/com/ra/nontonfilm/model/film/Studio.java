package com.ra.nontonfilm.model.film;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "studio")
public class Studio {

    @Id
    @Column(name = "studio_id")
    private Integer id;

    @Column(name = "studio_name", length = 15)
    private String name;

    @Column(name = "max_seat")
    private Integer maxSeat;
}
