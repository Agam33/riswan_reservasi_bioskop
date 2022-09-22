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
@Table(name = "genre")
public class Genre {

    @Id
    @Column(name = "genre_id")
    private int id;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private List<Film> films = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private FilmGenre genre;
}
