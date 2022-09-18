package com.ra.nontonfilm.repository;

import com.ra.nontonfilm.model.film.AvailableSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableSeatRepository extends JpaRepository<AvailableSeat, Integer> {
}
