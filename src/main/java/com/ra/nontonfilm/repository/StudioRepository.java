package com.ra.nontonfilm.repository;

import com.ra.nontonfilm.model.film.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Integer> {
}
