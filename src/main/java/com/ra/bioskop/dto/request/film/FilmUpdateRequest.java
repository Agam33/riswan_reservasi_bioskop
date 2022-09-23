package com.ra.bioskop.dto.request.film;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FilmUpdateRequest {
    private String filmCode;
    private String newName;
}
