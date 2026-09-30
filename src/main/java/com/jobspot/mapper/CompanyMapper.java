package com.jobspot.mapper;

import com.jobspot.dto.CompanyRequestDto;
import com.jobspot.dto.CompanyResponseDto;
import com.jobspot.entity.Company;

public class CompanyMapper {

    public static CompanyResponseDto toDto(Company company){
        return CompanyResponseDto.builder()
                .companyName(company.getCompanyName())
                .address(company.getAddress())
                .phone(company.getPhone())
                .city(company.getCity())
                .website(company.getWebsite())
                .description(company.getDescription())
                .industry(company.getIndustry())
                .build();
    }
    public static Company toEntity(CompanyRequestDto request){
        return Company.builder()
                .address(request.getAddress())
                .companyName(request.getCompanyName())
                .industry(request.getIndustry())
                .website(request.getWebsite())
                .phone(request.getPhone())
                .city(request.getCity())
                .description(request.getDescription())
                .build();
    }
}
