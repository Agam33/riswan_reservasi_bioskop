package com.ra.nontonfilm.dto.request.film;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FilmUpdateRequest {
    private String code;
    private String newName;
}
