package com.ra.bioskop.dto.request.film;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class FilmRequest {

    @NotNull
    private String title;

    @NotNull
    private String overview;

    @NotNull
    private Integer runtime;

    @NotNull
    private LocalDate releaseDate;
    private boolean onShow;
    private Integer genre;
}
