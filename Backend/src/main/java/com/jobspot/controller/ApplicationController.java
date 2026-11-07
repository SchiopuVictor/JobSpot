package com.jobspot.controller;

import com.jobspot.dto.ApplicationRequestDto;
import com.jobspot.dto.ApplicationResponseDto;
import com.jobspot.entity.Application;
import com.jobspot.entity.ApplicationStatus;
import com.jobspot.mapper.ApplicationMapper;
import com.jobspot.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/application")
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationResponseDto> createApplication(
            @Valid @RequestBody ApplicationRequestDto request)
    {
        return ResponseEntity.ok(ApplicationMapper
                .toDto(applicationService.createApplication(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponseDto> getApplication(
            @PathVariable Long id)
    {
        return ResponseEntity.ok(ApplicationMapper
                .toDto(applicationService.getApplication(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponseDto> updateApplication(
            @Valid @RequestBody ApplicationRequestDto request,
            @PathVariable Long id)
    {
        return ResponseEntity.ok(ApplicationMapper
                .toDto(applicationService.updateApplication(request,id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id)
    {
        applicationService.deleteApplication(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
