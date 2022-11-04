package com.ra.bioskop.service;

import com.ra.bioskop.dto.mapper.StudioMapper;
import com.ra.bioskop.dto.model.studio.StudioDTO;
import com.ra.bioskop.exception.BioskopException;
import com.ra.bioskop.exception.ExceptionType;
import com.ra.bioskop.model.film.Studio;
import com.ra.bioskop.repository.StudioRepository;
import com.ra.bioskop.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudioServiceImpl implements StudioService {

    private final StudioRepository studioRepository;

    public StudioServiceImpl(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }


    @Override
    public void addStudios(List<StudioDTO> studioDTOList) {
        List<Studio> studios = studioDTOList.stream().map(StudioMapper::dtoToEntity)
                .toList();
        studioRepository.saveAll(studios);
    }

    @Override
    public List<StudioDTO> getAllStudio() {
        List<Studio> studios = studioRepository.findAll();
        if(studios.isEmpty())
            throw BioskopException.throwException(ExceptionType.NOT_FOUND, HttpStatus.NOT_FOUND, Constants.NOT_FOUND_MSG);
        return studios.stream().map(StudioMapper::entityToDto).toList();
    }
}
