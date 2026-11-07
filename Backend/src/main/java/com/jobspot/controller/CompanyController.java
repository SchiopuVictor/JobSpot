package com.jobspot.controller;

import com.jobspot.dto.CompanyRequestDto;
import com.jobspot.dto.CompanyResponseDto;
import com.jobspot.mapper.CompanyMapper;
import com.jobspot.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponseDto> createCompany(
            @Valid @RequestBody CompanyRequestDto request)
    {
        return ResponseEntity.ok(CompanyMapper
                .toDto(companyService.createCompany(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getCompany(@PathVariable Long id){
        return ResponseEntity.ok(CompanyMapper.toDto(companyService.getCompany(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> updateCompany(
            @Valid @RequestBody CompanyRequestDto request,
            @PathVariable Long id)
    {
        return ResponseEntity.ok(CompanyMapper.toDto(companyService.updateCompany(request,id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> deleteCompany(@PathVariable Long id){
        companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
