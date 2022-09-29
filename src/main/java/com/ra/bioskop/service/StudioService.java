package com.ra.bioskop.service;

import com.ra.bioskop.dto.model.studio.StudioDTO;

import java.util.List;

public interface StudioService {

    void addStudios(List<StudioDTO> studioRequestList);

    List<StudioDTO> getAllStudio();
}
