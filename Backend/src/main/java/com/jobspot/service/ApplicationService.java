package com.jobspot.service;

import com.jobspot.dto.ApplicationRequestDto;
import com.jobspot.entity.*;
import com.jobspot.exceptions.ApplicationNotFoundException;
import com.jobspot.exceptions.CvNotFoundException;
import com.jobspot.exceptions.InternshipNotFoundException;
import com.jobspot.exceptions.StudentNotFoundException;
import com.jobspot.mapper.ApplicationMapper;
import com.jobspot.repository.ApplicationRepository;
import com.jobspot.repository.CvRepository;
import com.jobspot.repository.InternshipRepository;
import com.jobspot.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final CvRepository cvRepository;
    private final InternshipRepository internshipRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public Application createApplication(ApplicationRequestDto request){
        Application application = ApplicationMapper.toEntity(request);

        Cv cv = cvRepository.findById(request.getCv_id())
                .orElseThrow(CvNotFoundException::new);

        Internship internship = internshipRepository.findById(request.getInternship_id())
                .orElseThrow(InternshipNotFoundException::new);

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        assert principal != null;
        Long userId = Long.valueOf(principal.toString());


        Student student = studentRepository.findById(userId)
                .orElseThrow(StudentNotFoundException::new);

        application.setCv(cv);
        application.setStatus(ApplicationStatus.Applied);
        application.setAppliedAt(LocalDate.now());
        application.setStudent(student);
        application.setInternship(internship);

        return applicationRepository.save(application);
    }

    public Application getApplication(Long id){
        return applicationRepository.findById(id)
                .orElseThrow(ApplicationNotFoundException::new);
    }

    @Transactional
    public Application updateApplication(ApplicationRequestDto request,Long id){
      Application application = applicationRepository.findById(id)
              .orElseThrow(ApplicationNotFoundException::new);

        Cv cv = cvRepository.findById(request.getCv_id())
                .orElseThrow(CvNotFoundException::new);

        Internship internship = internshipRepository.findById(request.getInternship_id())
                .orElseThrow(InternshipNotFoundException::new);

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        assert principal != null;
        Long userId = Long.valueOf(principal.toString());

        Student student = studentRepository.findById(userId)
                .orElseThrow(StudentNotFoundException::new);

        application.setStatus(ApplicationStatus.Applied);
        application.setStudent(student);
        application.setCoverLetter(request.getCoverLetter());
        application.setCv(cv);
        application.setInternship(internship);
        application.setAppliedAt(LocalDate.now());

        return applicationRepository.save(application);
    }

    @Transactional
    public void deleteApplication (Long id){
        applicationRepository.findById(id)
                .orElseThrow(ApplicationNotFoundException::new);
        applicationRepository.deleteById(id);
    }

}
