package com.ra.bioskop.repository;

import com.ra.bioskop.model.film.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {

    Optional<Show> findByShowAt(String showAt);
}
