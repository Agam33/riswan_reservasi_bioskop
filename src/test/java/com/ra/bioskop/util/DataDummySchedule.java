package com.ra.bioskop.util;

import com.ra.bioskop.dto.model.film.DetailScheduleDTO;
import com.ra.bioskop.model.film.Film;
import com.ra.bioskop.model.film.Schedule;
import com.ra.bioskop.model.film.Studio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DataDummySchedule {

    private final List<Schedule> SCHEDULES = new ArrayList<>();

    private final List<DetailScheduleDTO> DETAIL_SCHEDULE = new ArrayList<>();

    public DataDummySchedule() {
        Schedule sc1 = new Schedule();
        sc1.setId("sc-01");
        sc1.setStudio(new Studio());
        sc1.setFilm(new Film());
        sc1.setPrice(new BigDecimal(50_000));
        sc1.setShowAt(LocalDate.parse("2022-10-10"));
        sc1.setCreatedAt(LocalDateTime.now());
        sc1.setEndTime(LocalTime.now());

        Schedule sc2 = new Schedule();
        sc2.setId("sc-02");
        sc2.setStudio(new Studio());
        sc2.setFilm(new Film());
        sc2.setPrice(new BigDecimal(50_000));
        sc2.setShowAt(LocalDate.parse("2022-10-10"));
        sc2.setCreatedAt(LocalDateTime.now());
        sc2.setEndTime(LocalTime.now());

        Schedule sc3 = new Schedule();
        sc3.setId("sc-03");
        sc3.setStudio(new Studio());
        sc3.setFilm(new Film());
        sc3.setPrice(new BigDecimal(50_000));
        sc3.setShowAt(LocalDate.parse("2022-10-10"));
        sc3.setCreatedAt(LocalDateTime.now());
        sc3.setEndTime(LocalTime.now());

        SCHEDULES.add(sc1);
        SCHEDULES.add(sc2);
        SCHEDULES.add(sc3);

        DetailScheduleDTO detailSchedule1 = new DetailScheduleDTO();
        detailSchedule1.setDate(LocalDate.now());
        detailSchedule1.setEndTime(LocalTime.now());
        detailSchedule1.setPrice(sc1.getPrice());
        detailSchedule1.setFilmName("Jurassic World Dominion (2022)");
        detailSchedule1.setStudioName("A");

        DetailScheduleDTO detailSchedule2 = new DetailScheduleDTO();
        detailSchedule2.setDate(LocalDate.now());
        detailSchedule2.setEndTime(LocalTime.now());
        detailSchedule2.setPrice(sc1.getPrice());
        detailSchedule2.setFilmName("Jurassic World Dominion (2022)");
        detailSchedule2.setStudioName("A");

        DetailScheduleDTO detailSchedule3 = new DetailScheduleDTO();
        detailSchedule3.setDate(LocalDate.now());
        detailSchedule3.setEndTime(LocalTime.now());
        detailSchedule3.setPrice(sc1.getPrice());
        detailSchedule3.setFilmName("Jurassic World Dominion (2022)");
        detailSchedule3.setStudioName("A");

        DETAIL_SCHEDULE.add(detailSchedule1);
        DETAIL_SCHEDULE.add(detailSchedule2);
        DETAIL_SCHEDULE.add(detailSchedule3);

    }

    public List<Schedule> getSchedules() {
        return SCHEDULES;
    }

    public List<DetailScheduleDTO> getDetailSchedules() {
        return DETAIL_SCHEDULE;
    }
}
