package com.jobspot.controller;

import com.jobspot.dto.InterviewRequestDto;
import com.jobspot.dto.InterviewResponseDto;
import com.jobspot.mapper.InterviewMapper;
import com.jobspot.service.InterviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/interview")
public class InterviewController {
    private final InterviewService interviewService;

    @PostMapping
    public ResponseEntity<InterviewResponseDto> createInterview(
            @Valid @RequestBody InterviewRequestDto request) {
        return ResponseEntity.ok(InterviewMapper
                .toDto(interviewService.createInterview(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterviewResponseDto> updateInterview(
            @Valid @RequestBody InterviewRequestDto request,
            @PathVariable Long id) {
        return ResponseEntity.ok(InterviewMapper
                .toDto(interviewService.updateInterview(request, id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterviewResponseDto> getInterview(
            @PathVariable Long id) {
        return ResponseEntity.ok(InterviewMapper
                .toDto(interviewService.getInterview(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterview(@PathVariable Long id)
    {
        interviewService.deleteInterview(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
