package com.jobspot.controller;

import com.jobspot.dto.InternshipRequestDto;
import com.jobspot.dto.InternshipResponseDto;
import com.jobspot.mapper.InternshipMapper;
import com.jobspot.service.InternshipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/internship")
public class InternshipController {

    private final InternshipService internshipService;

    @PostMapping
    public ResponseEntity<InternshipResponseDto> createInternship
            (@Valid @RequestBody InternshipRequestDto request)
    {
        return ResponseEntity.ok(InternshipMapper
                .toDto(internshipService.createInternship(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternshipResponseDto> getInternship(@PathVariable Long id){
        return ResponseEntity.ok(InternshipMapper.toDto(internshipService.getInternship(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternshipResponseDto> updateInternship(
            @PathVariable Long id,
            @RequestBody InternshipRequestDto request)
    {
        return ResponseEntity.ok(InternshipMapper
                .toDto(internshipService.updateInternship(request,id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInternship(@PathVariable Long id){

        internshipService.deleteInternship(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
