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
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InternshipService {
    private final InternshipRepository internshipRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public Internship createInternship(InternshipRequestDto request){
        Company company = companyRepository.findById(request.getCompany_id())
                .orElseThrow(CompanyNotFoundException::new);

        Internship internship = InternshipMapper.toEntity(request);

        internship.setCreatedAt(LocalDate.now());
        internship.setCompany(company);

        return internshipRepository.save(internship);
    }

    public Internship getInternship(Long id){
        return internshipRepository.findById(id)
                .orElseThrow(InternshipNotFoundException::new);
    }

    @Transactional
    public Internship updateInternship(InternshipRequestDto request,Long id){

        Company company = companyRepository.findById(request.getCompany_id())
                .orElseThrow(CompanyNotFoundException::new);

        Internship internship = internshipRepository.findById(id)
                .orElseThrow(InternshipNotFoundException::new);

        internship.setCompany(company);
        internship.setDescription(request.getDescription());
        internship.setDuration(request.getDuration());
        internship.setRequirements(request.getRequirements());
        internship.setResponsibilities(request.getResponsibilities());
        internship.setSalary(request.getSalary());
        internship.setStatus(request.getStatus());
        internship.setType(request.getType());
        internship.setCreatedAt(LocalDate.now());
        internship.setTitle(request.getTitle());
        internship.setLocation(request.getLocation());
        internship.setApplicationDeadline(request.getApplicationDeadline());
        internship.setAvailablePositions(request.getAvailablePositions());

        return internshipRepository.save(internship);
    }

    @Transactional
    public void deleteInternship(Long id){
        internshipRepository.findById(id)
                .orElseThrow(InternshipNotFoundException::new);

        internshipRepository.deleteById(id);
    }

}
