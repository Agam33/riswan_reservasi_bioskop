package com.ra.nontonfilm.model.film;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @Column(name = "genre_id")
    private int id;

    @ManyToMany(mappedBy = "genres")
    private Collection<Film> films;

    @Enumerated(EnumType.STRING)
    private FilmGenre genre;
}
