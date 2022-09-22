package com.ra.nontonfilm.model.film;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private SeatNo id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @JsonIgnore
    @ManyToMany(mappedBy = "seats", fetch = FetchType.LAZY)
    private List<Studio> studios = new ArrayList<>();

}
