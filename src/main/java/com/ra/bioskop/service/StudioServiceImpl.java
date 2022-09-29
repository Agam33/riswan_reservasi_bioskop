package com.ra.bioskop.service;

import com.ra.bioskop.dto.mapper.StudioMapper;
import com.ra.bioskop.dto.model.studio.StudioDTO;
import com.ra.bioskop.exception.BioskopException;
import com.ra.bioskop.exception.ExceptionType;
import com.ra.bioskop.model.film.Studio;
import com.ra.bioskop.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudioServiceImpl implements StudioService {

    @Autowired
    private StudioRepository studioRepository;


    @Override
    public void addStudios(List<StudioDTO> studioDTOList) {
        List<Studio> studios = studioDTOList.stream().map(StudioMapper::dtoToEntity)
                .collect(Collectors.toList());
        studioRepository.saveAll(studios);
    }

    @Override
    public List<StudioDTO> getAllStudio() {
        List<Studio> studios = studioRepository.findAll();
        if(studios.isEmpty())
            throw BioskopException.throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND, "Tidak ditemukan");
        return studios.stream().map(StudioMapper::entityToDto).collect(Collectors.toList());
    }
}
