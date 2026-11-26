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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CvService
{
    private final CvRepository cvRepository;
    private final StudentRepository studentRepository;


    public List<Cv> getCvByStudentId(long studentId){
        studentRepository.findById(studentId)
                .orElseThrow(StudentNotFoundException::new);

        return cvRepository.findCvByStudentId(studentId);
    }

    @Transactional
    public Cv createCv(MultipartFile file ) throws IOException {

        if(file == null || file.isEmpty()){
            throw new IllegalArgumentException("Fisierul este gol");
        }

        if (!"application/pdf".equals(file.getContentType())) {
            throw new IllegalArgumentException("Este permis doar formatul PDF!");
        }

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Long student_Id = Long.valueOf(Objects.requireNonNull(principal).toString());

        Student student = studentRepository
                .findById(student_Id).orElseThrow(StudentNotFoundException::new);

        String originalName = file.getOriginalFilename();
        String storedName = UUID.randomUUID() +".pdf";

        Path uploadPath = Paths.get("uploads/cvs");
        Files.createDirectories(uploadPath);

        Path filePath = uploadPath.resolve(storedName);
        Files.copy(file.getInputStream(),filePath);


        Cv cv = new Cv();

        cv.setStudent(student);
        cv.setFileName(originalName);
        cv.setFilePath(filePath.toString());
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
