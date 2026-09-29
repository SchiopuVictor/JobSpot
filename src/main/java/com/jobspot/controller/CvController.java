package com.jobspot.controller;

import com.jobspot.dto.CvRequestDto;
import com.jobspot.dto.CvResponseDto;
import com.jobspot.mapper.CvMapper;
import com.jobspot.service.CvService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cv")
public class CvController {

    private final CvService cvService;

    @GetMapping("/{id}")
    public ResponseEntity<CvResponseDto> getCv(@PathVariable Long id){
        return ResponseEntity.ok(CvMapper.toDto(cvService.getCv(id)));
    }

    @PostMapping
    public ResponseEntity<CvResponseDto> createCv(@Valid @RequestBody CvRequestDto request){
        return ResponseEntity.ok(CvMapper.toDto(cvService.createCv(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CvResponseDto> updateCv(
            @Valid @RequestBody CvRequestDto request,
            @PathVariable Long id) {
        return ResponseEntity.ok(CvMapper.toDto(cvService.updateCv(request, id)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCv(@PathVariable Long id){
        cvService.deleteCv(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
