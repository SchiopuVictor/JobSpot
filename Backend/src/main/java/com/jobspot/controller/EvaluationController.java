package com.jobspot.controller;

import com.jobspot.dto.EvaluationRequestDto;
import com.jobspot.dto.EvaluationResponseDto;
import com.jobspot.mapper.EvaluationMapper;
import com.jobspot.service.EvaluationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evaluation")
@RequiredArgsConstructor
public class EvaluationController {
    private final EvaluationService evaluationService;

    @PostMapping
    public ResponseEntity<EvaluationResponseDto> createEvaluation(
            @Valid @RequestBody EvaluationRequestDto request)
    {
        return ResponseEntity.ok(EvaluationMapper
                .toDto(evaluationService.createEvaluation(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationResponseDto> updateEvaluation(
            @Valid @RequestBody  EvaluationRequestDto request,
            @PathVariable Long id)
    {
        return ResponseEntity.ok(EvaluationMapper
                .toDto(evaluationService.updateEvaluation(request,id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationResponseDto> getEvaluation(
            @PathVariable Long id)
    {
        return ResponseEntity.ok(EvaluationMapper
                .toDto(evaluationService.getEvaluation(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id){
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


}
