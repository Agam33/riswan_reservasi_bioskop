package com.ra.bioskop.model.film;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ra.bioskop.model.film.embedded.SeatNo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @JsonIgnore
    @ManyToMany(mappedBy = "seats", fetch = FetchType.LAZY)
    private List<Studio> studios = new ArrayList<>();

}
