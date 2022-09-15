package com.ra.nontonfilm.model.film;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @Column(name = "genre_id")
    private int id;

    @ManyToMany(mappedBy = "genres")
    private List<Film> films = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private FilmGenre genre;
}
