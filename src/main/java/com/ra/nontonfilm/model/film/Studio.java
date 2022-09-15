package com.ra.nontonfilm.model.film;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "studio")
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "studio_id")
    private Integer id;

    @Column(name = "studio_name", length = 10)
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "studio_seat",
        joinColumns = @JoinColumn(name = "studio_id"),
                inverseJoinColumns = @JoinColumn(name = "seat_id") )
    private List<Seat> seats = new ArrayList<>();
}
