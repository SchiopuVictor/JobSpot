package com.jobspot.service;

import com.jobspot.dto.CvRequestDto;
import com.jobspot.entity.Cv;
import com.jobspot.entity.User;
import com.jobspot.mapper.CvMapper;
import com.jobspot.repository.CvRepository;
import com.jobspot.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CvService
{
    private final CvRepository cvRepository;
    private final UserRepository userRepository;

    @Transactional
    public Cv createCv(CvRequestDto request){
        User student = userRepository
                .findById(request.getUser_Id()).orElseThrow(()->new RuntimeException("User not found!!"));
        Cv cv = CvMapper.toEntity(request);
        cv.setStudent(student);
        cv.setUploadedAt(LocalDate.now());

        return cvRepository.save(cv);

    }

    public Cv getCv(Long id){
       return cvRepository
               .findById(id).orElseThrow(()->new RuntimeException("Cv not found!!"));
    }

    @Transactional
    public Cv updateCv(CvRequestDto request, Long id){
        User student = userRepository
                .findById(request.getUser_Id()).orElseThrow(()->new RuntimeException("User not found!!"));
        Cv cv = cvRepository
                .findById(id).orElseThrow(()->new RuntimeException("Cv not found!!"));

        cv.setUploadedAt(LocalDate.now());
        cv.setFileName(request.getFileName());
        cv.setStudent(student);

        return cvRepository.save(cv);

    }

    @Transactional
    public void deleteCv(Long id){
        cvRepository
                .findById(id).orElseThrow(()->new RuntimeException("Cv not found!!"));
        cvRepository.deleteById(id);

    }

}
