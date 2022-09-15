package com.ra.nontonfilm.model.film;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
    private List<Genre> genres = new ArrayList<>();

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "on_show")
    private boolean onShow;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

}
