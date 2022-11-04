package com.ra.bioskop.util;

import com.ra.bioskop.model.film.Film;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataDummyFilm {

    private final List<Film> FILMS = new ArrayList<>();


    public DataDummyFilm() {
        Film film1 = new Film();
        film1.setTitle("Fall (2022)");
        film1.setFilmCode("film-001");
        film1.setOnShow(true);
        film1.setOverview("Untuk sahabat Becky dan Hunter, hidup adalah tentang menaklukkan ketakutan dan mendorong batas.");
        film1.setRuntime(90);
        film1.setReleaseDate(LocalDate.now());
        film1.setPopularity(100);
        film1.setUpdatedAt(LocalDateTime.now());
        film1.setSchedules(new ArrayList<>());

        Film film2 = new Film();
        film2.setTitle("Minions: Kebangkitan Gru (2022)");
        film2.setFilmCode("film-002");
        film2.setOnShow(true);
        film2.setOverview("seorang penggemar kelompok penjahat super yang dikenal sebagai Vicious 6, Gru menyusun rencana untuk menjadi cukup jahat untuk bergabung dengan mereka, dengan dukungan pengikutnya, para Minion.");
        film2.setRuntime(120);
        film2.setReleaseDate(LocalDate.now());
        film2.setPopularity(100);
        film2.setUpdatedAt(LocalDateTime.now());
        film2.setSchedules(new ArrayList<>());

        Film film3 = new Film();
        film3.setTitle("Jurassic World Dominion (2022)");
        film3.setFilmCode("film-003");
        film3.setOnShow(false);
        film3.setOverview("Four years after Isla Nublar was destroyed, dinosaurs now live—and hunt—alongside humans all over the world.");
        film3.setRuntime(120);
        film3.setReleaseDate(LocalDate.now());
        film3.setPopularity(100);
        film3.setUpdatedAt(LocalDateTime.now());
        film3.setSchedules(new ArrayList<>());

        FILMS.add(film1);
        FILMS.add(film2);
        FILMS.add(film3);
    }

    public List<Film> getAllFilm() {
        return FILMS;
    }

    public Optional<Film> getFilmById(String id) {
        return FILMS.stream()
                .filter(film -> film.getFilmCode().equals(id))
                .findFirst();
    }
}
