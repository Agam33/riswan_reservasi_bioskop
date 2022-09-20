package com.ra.nontonfilm.model.film;

import com.ra.nontonfilm.model.film.embedded.SeatNo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "seat")
public class Seat {

    @EmbeddedId
    private SeatNo seatNo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToMany(mappedBy = "seats", fetch = FetchType.LAZY)
    private List<Studio> studios = new ArrayList<>();

    @PrePersist
    public void onCreate() {
        updatedAt = new Date();
    }
}
