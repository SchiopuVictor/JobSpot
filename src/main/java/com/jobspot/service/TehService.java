package com.jobspot.service;

import com.jobspot.dto.TehRequestDto;
import com.jobspot.entity.Technology;
import com.jobspot.exceptions.TechnologyNotFoundException;
import com.jobspot.mapper.TehMapper;
import com.jobspot.repository.TehRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TehService {
    private final TehRepository tehRepository;

    @Transactional
    public Technology createTechnology(TehRequestDto request){
        return tehRepository.save(TehMapper.toEntity(request));

    }

    public Technology getTechnology(Long id){
       return tehRepository.findById(id)
               .orElseThrow(TechnologyNotFoundException::new);

    }

    @Transactional
    public Technology updateTechnology(TehRequestDto request, Long id){
        Technology technology = tehRepository.findById(id)
                .orElseThrow(TechnologyNotFoundException::new);

        technology.setName(request.getName());

        return tehRepository.save(technology);

    }

    @Transactional
    public void deleteTechnology(Long id){

        tehRepository.findById(id)
                .orElseThrow(TechnologyNotFoundException::new);
        tehRepository.deleteById(id);

    }

}
