package com.ra.bioskop.repository;

import com.ra.bioskop.model.film.Seat;
import com.ra.bioskop.model.film.embedded.SeatNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, SeatNo> {
}
