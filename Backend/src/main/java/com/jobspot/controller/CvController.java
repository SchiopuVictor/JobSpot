package com.jobspot.controller;

import com.jobspot.dto.CvRequestDto;
import com.jobspot.dto.CvResponseDto;
import com.jobspot.mapper.CvMapper;
import com.jobspot.service.CvService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cv")
public class CvController {

    private final CvService cvService;

    @GetMapping("/studentcv/{studentId}")
    public ResponseEntity<List<CvResponseDto>> getCvByStudentId(@PathVariable long studentId)
    {
        return ResponseEntity.ok().body(
                cvService.getCvByStudentId(studentId)
                    .stream()
                    .map(CvMapper::toDto)
                    .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CvResponseDto> getCv(@PathVariable Long id){
        return ResponseEntity.ok(CvMapper.toDto(cvService.getCv(id)));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CvResponseDto> createCv(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(CvMapper.toDto(cvService.createCv(file)));
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
