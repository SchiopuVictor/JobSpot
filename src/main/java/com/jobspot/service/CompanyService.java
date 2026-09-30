package com.jobspot.service;

import com.jobspot.dto.CompanyRequestDto;
import com.jobspot.entity.Company;
import com.jobspot.entity.User;
import com.jobspot.mapper.CompanyMapper;
import com.jobspot.repository.CompanyRepository;
import com.jobspot.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Transactional
    public Company createCompany(CompanyRequestDto request){

        User user =userRepository.findById(request.getUser())
                .orElseThrow(()->new RuntimeException("User not found!!"));

        Company company = CompanyMapper.toEntity(request);
        company.setUser(user);

        return companyRepository.save(company);
    }

    public Company getCompany(Long id){

        return companyRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Company not found!!"));
    }

    @Transactional
    public Company updateCompany(CompanyRequestDto request, Long id){
        Company company = companyRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Company not found"));

        User user = userRepository.findById(company.getUser().getId())
                .orElseThrow(()->new RuntimeException("User not found!!"));

        company.setUser(user);
        company.setCompanyName(request.getCompanyName());
        company.setDescription(request.getDescription());
        company.setCity(request.getCity());
        company.setPhone(request.getPhone());
        company.setAddress(request.getAddress());
        company.setIndustry(request.getIndustry());
        company.setWebsite(request.getWebsite());

        return companyRepository.save(company);
    }

    @Transactional
    public void deleteCompany(Long id){
        companyRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Company not found!!"));
        companyRepository.deleteById(id);
    }

}
