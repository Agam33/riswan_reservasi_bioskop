package com.ra.bioskop.repository;

import com.ra.bioskop.model.film.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

//    @Query(SCHEDULE_START_TIME_AND_SHOW_AT)
//    Optional<Schedule> findByStartTimeAndShowAt(
//            @Param("startTime") String startTime,
//            @Param("showAt") String showAt);

    Optional<Schedule> findByShowAt(String showAt);
}
