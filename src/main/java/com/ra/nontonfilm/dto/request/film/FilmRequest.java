package com.ra.nontonfilm.dto.request.film;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FilmRequest {
    private String title;
    private Integer runtime;
    private String releaseDate;
    private boolean onShow;
    private Integer genre;
}
