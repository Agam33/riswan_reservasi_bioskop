package com.ra.bioskop.repository;

import com.ra.bioskop.model.film.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Integer> {
    Studio findStudioByName(String name);
}
