package com.jobspot.controller;

import com.jobspot.dto.InternshipRequestDto;
import com.jobspot.dto.InternshipResponseDto;
import com.jobspot.mapper.InternshipMapper;
import com.jobspot.service.InternshipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/internship")
public class InternshipController {

    private final InternshipService internshipService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InternshipResponseDto> createInternship
            (@Valid @RequestPart("internship") InternshipRequestDto request,
             @RequestPart(value = "image",required = false)MultipartFile file
             )

    {
        return ResponseEntity.ok(InternshipMapper
                .toDto(internshipService.createInternship(request,file)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<InternshipResponseDto>> getAllInternships(){
        return ResponseEntity.ok(
                internshipService
                        .getAllInternships()
                        .stream()
                        .map(InternshipMapper::toDto)
                        .toList());
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
