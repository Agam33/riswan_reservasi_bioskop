package com.ra.bioskop.repository;

import com.ra.bioskop.dto.model.film.DetailScheduleDTO;
import com.ra.bioskop.model.film.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.ra.bioskop.repository.query.ScheduleQuery.SCHEDULE_DETAIL_BY_DATE;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    @Query(value = SCHEDULE_DETAIL_BY_DATE)
    List<DetailScheduleDTO> findByShowAt(@Param("showAt") LocalDate showAt);

}
