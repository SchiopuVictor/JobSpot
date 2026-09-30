package com.jobspot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompanyResponseDto {

    private String companyName;
    private String description;
    private String website;
    private String phone;
    private String address;
    private String city;
    private String industry;
}
