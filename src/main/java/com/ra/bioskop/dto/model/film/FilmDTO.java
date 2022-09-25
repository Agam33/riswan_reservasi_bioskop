package com.ra.bioskop.dto.model.film;

import com.ra.bioskop.model.film.Genre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class FilmDTO {
    private String filmCode;
    private String title;
    private String overview;
    private Integer runtime;
    private LocalDate releaseDate;
    private boolean onShow;
    private List<Genre> genres = new ArrayList<>();
}
