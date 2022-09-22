package com.ra.bioskop.repository;

import com.ra.bioskop.model.film.OnShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OnShowRepository extends JpaRepository<OnShow, Integer> {

    Optional<OnShow> findByShowAt(String showAt);
}
