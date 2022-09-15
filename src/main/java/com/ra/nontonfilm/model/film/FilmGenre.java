package com.ra.nontonfilm.model.film;

import java.util.Arrays;
import java.util.Optional;

public enum FilmGenre {
    COMEDY, ADVENTURE, CRIME, HORROR, THRILLER, DRAMA, FANTASY, UNKNOWN;

    public static FilmGenre getById(Integer id) {
        Optional<FilmGenre> filmGenres = Arrays.stream(values())
                .filter(filmGenre -> filmGenre.ordinal() == id)
                .findFirst();
        return filmGenres.orElse(FilmGenre.UNKNOWN);
    }
}
