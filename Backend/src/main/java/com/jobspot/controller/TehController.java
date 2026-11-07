package com.jobspot.controller;

import com.jobspot.dto.TehRequestDto;
import com.jobspot.dto.TehResponseDto;
import com.jobspot.mapper.TehMapper;
import com.jobspot.service.TehService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teh")
@RequiredArgsConstructor
public class TehController {
    private final TehService tehService;

    @PostMapping
    public ResponseEntity<TehResponseDto> createTechnology(
            @Valid @RequestBody TehRequestDto request){
        return ResponseEntity.ok(TehMapper
                .toDto(tehService.createTechnology(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TehResponseDto> getTechnology(
            @PathVariable Long id)
    {
        return ResponseEntity.ok(TehMapper
                .toDto(tehService.getTechnology(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TehResponseDto>updateTechnology(
            @Valid @RequestBody TehRequestDto request,
            @PathVariable Long id)
    {
        return ResponseEntity.ok(TehMapper
                .toDto(tehService.updateTechnology(request,id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnology(
            @PathVariable Long id){
      tehService.deleteTechnology(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
