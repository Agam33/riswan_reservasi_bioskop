package com.ra.bioskop.dto.request.film;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class FilmRequest {
    private String title;
    private String overview;
    private Integer runtime;
    private Date releaseDate;
    private boolean onShow;
    private Integer genre;
}
