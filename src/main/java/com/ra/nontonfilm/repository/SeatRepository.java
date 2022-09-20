package com.ra.nontonfilm.repository;

import com.ra.nontonfilm.model.film.Seat;
import com.ra.nontonfilm.model.film.embedded.SeatNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, SeatNo> {
}
