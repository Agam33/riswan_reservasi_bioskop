package com.ra.bioskop.model.film;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "studio",
    indexes = @Index(name = "idx_studio_name", columnList = "studio_name", unique = true))
public class Studio {

    @Id
    @Column(name = "studio_id")
    private Integer id;

    @Column(name = "studio_name", length = 15)
    private String name;

    @Column(name = "max_seat")
    private Integer maxSeat;

    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY)
    private List<Schedule> schedules;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = {@JoinColumn(name = "studio_id")},
                inverseJoinColumns = {
                        @JoinColumn(name = "seat_no"),
                        @JoinColumn(name = "seat_row")})
    private List<Seat> seats = new ArrayList<>();
}
