package com.ra.bioskop.dto.request.film;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @NotNull(message = "yyyy-MM-dd")
    @Schema(description = "input: yyyy-MM-dd", type = "string", example = "2022-10-10")
    private String releaseDate;
    private boolean onShow;
    private Integer genre;
}
