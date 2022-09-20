package com.ra.nontonfilm.dto.model.film;

import com.ra.nontonfilm.model.film.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class FilmDTO {
    private String code;
    private String title;
    private String overview;
    private Integer runtime;
    private String releaseDate;
    private boolean onShow;
    private List<Genre> genres = new ArrayList<>();
}
