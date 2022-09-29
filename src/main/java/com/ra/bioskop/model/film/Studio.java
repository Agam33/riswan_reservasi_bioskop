package com.ra.bioskop.model.film;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "studio")
public class Studio {

    @Id
    @Column(name = "studio_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "studio_name", length = 15)
    private String name;

    @Column(name = "max_seat")
    private Integer maxSeat;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = {@JoinColumn(name = "studio_id")},
                inverseJoinColumns = {
                        @JoinColumn(name = "seat_no"),
                        @JoinColumn(name = "seat_row")})
    private List<Seat> seats = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();
}
