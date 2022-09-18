package com.ra.nontonfilm.model.film;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "film")
public class Film {

    @Id
    @Column(name = "film_code")
    private String code;

    @Column(name = "title")
    private String title;

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "overview")
    private String overview;

    @Column(name = "on_show")
    private boolean onShow;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_genre",
            joinColumns = @JoinColumn(name = "film_code"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<Schedule> schedules = new ArrayList<>();
}
