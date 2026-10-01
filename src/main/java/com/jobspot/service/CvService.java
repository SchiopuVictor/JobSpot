package com.jobspot.service;

import com.jobspot.dto.CvRequestDto;
import com.jobspot.entity.Cv;
import com.jobspot.entity.Student;
import com.jobspot.exceptions.CvNotFoundException;
import com.jobspot.exceptions.StudentNotFoundException;
import com.jobspot.mapper.CvMapper;
import com.jobspot.repository.CvRepository;
import com.jobspot.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CvService
{
    private final CvRepository cvRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public Cv createCv(CvRequestDto request){
        Student student = studentRepository
                .findById(request.getUser_Id()).orElseThrow(StudentNotFoundException::new);

        Cv cv = CvMapper.toEntity(request);
        cv.setStudent(student);
        cv.setUploadedAt(LocalDate.now());

        return cvRepository.save(cv);

    }

    public Cv getCv(Long id){
       return cvRepository
               .findById(id).orElseThrow(CvNotFoundException::new);
    }

    @Transactional
    public Cv updateCv(CvRequestDto request, Long id){
        Student student = studentRepository
                .findById(request.getUser_Id()).orElseThrow(StudentNotFoundException::new);

        Cv cv = cvRepository
                .findById(id).orElseThrow(CvNotFoundException::new);

        cv.setUploadedAt(LocalDate.now());
        cv.setFileName(request.getFileName());
        cv.setStudent(student);

        return cvRepository.save(cv);

    }

    @Transactional
    public void deleteCv(Long id){
        cvRepository
                .findById(id).orElseThrow(CvNotFoundException::new);
        cvRepository.deleteById(id);

    }

}
