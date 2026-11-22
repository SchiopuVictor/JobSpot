package com.jobspot.service;

import com.jobspot.dto.InternshipRequestDto;
import com.jobspot.entity.Company;
import com.jobspot.entity.Internship;
import com.jobspot.exceptions.CompanyNotFoundException;
import com.jobspot.exceptions.InternshipNotFoundException;
import com.jobspot.mapper.InternshipMapper;
import com.jobspot.repository.CompanyRepository;
import com.jobspot.repository.InternshipRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InternshipService {
    private final InternshipRepository internshipRepository;
    private final CompanyRepository companyRepository;

    public List<Internship> getAllInternships(){
        return internshipRepository.findAll();
    }

    @Transactional
    public Internship createInternship(InternshipRequestDto request,
                                       MultipartFile file){

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        assert principal != null;
        Long userId = Long.valueOf(principal.toString());

        Company company = companyRepository.findByUserId(userId)
                .orElseThrow(CompanyNotFoundException::new);

        Internship internship = InternshipMapper.toEntity(request);

        internship.setCreatedAt(LocalDate.now());
        internship.setCompany(company);

        try{

            if( file != null && !file.isEmpty()) {

                Path uploadPath = Paths.get("uploads/internships");

                Files.createDirectories(uploadPath);

                String extensions = Objects.requireNonNull(file.getOriginalFilename())
                        .substring(file.getOriginalFilename().lastIndexOf("."));

                String fileName = UUID.randomUUID() + extensions;

                Path filePath = uploadPath.resolve(fileName);

                Files.copy(file.getInputStream(), filePath);

                String imgUrl = "/uploads/internships/" + fileName;

                internship.setImageUrl(imgUrl);
            }
        }catch (Exception e){
            throw new RuntimeException("Error image upload", e);
        }

        return internshipRepository.save(internship);
    }

    public Internship getInternship(Long id){
        return internshipRepository.findById(id)
                .orElseThrow(InternshipNotFoundException::new);
    }

    @Transactional
    public Internship updateInternship(InternshipRequestDto request,Long id){

        Long userId = (Long) Objects.requireNonNull(SecurityContextHolder
                        .getContext()
                        .getAuthentication())
                .getPrincipal();

        assert userId != null;
        Company company = companyRepository.findById(userId)
                .orElseThrow(CompanyNotFoundException::new);

        Internship internship = internshipRepository.findById(id)
                .orElseThrow(InternshipNotFoundException::new);

        internship.setCompany(company);
        internship.setDescription(request.getDescription());
        internship.setDuration(request.getDuration());
        internship.setImageUrl(request.getImgUrl());
        internship.setRequirements(request.getRequirements());
        internship.setResponsibilities(request.getResponsibilities());
        internship.setSalary(request.getSalary());
        internship.setStatus(request.getStatus());
        internship.setType(request.getType());
        internship.setCreatedAt(LocalDate.now());
        internship.setTitle(request.getTitle());
        internship.setLocation(request.getLocation());
        internship.setApplicationDeadline(request.getApplicationDeadline());
        internship.setWorkingSchedule(request.getWorking_schedule());
        return internshipRepository.save(internship);
    }

    @Transactional
    public void deleteInternship(Long id){
        internshipRepository.findById(id)
                .orElseThrow(InternshipNotFoundException::new);

        internshipRepository.deleteById(id);
    }

}
