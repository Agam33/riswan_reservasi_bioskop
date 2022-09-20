package com.ra.nontonfilm.model.film.embedded;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class SeatNo implements Serializable {
    @Column(name = "seat_no")
    private Integer seatNo;

    @Column(name = "seat_row")
    private String seatRow;
}
