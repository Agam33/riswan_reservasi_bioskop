package com.ra.nontonfilm.model.film;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "film")
public class Film {
    @Id
    @Column(name = "film_code")
    private String code;

    @Column(name = "title")
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "film_genre",
                joinColumns = @JoinColumn(name = "film_code"),
                    inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Collection<Genre> genres;

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "on_show")
    private boolean onShow;

}
