package com.jobspot.service;

import com.jobspot.dto.EvaluationRequestDto;
import com.jobspot.entity.Evaluation;
import com.jobspot.entity.Internship;
import com.jobspot.entity.Student;
import com.jobspot.mapper.EvaluationMapper;
import com.jobspot.repository.EvaluationRepository;
import com.jobspot.repository.InternshipRepository;
import com.jobspot.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final InternshipRepository internshipRepository;
    private final EvaluationRepository evaluationRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public Evaluation createEvaluation(EvaluationRequestDto request){

        Evaluation evaluation = EvaluationMapper.toEntity(request);

        Internship internship = internshipRepository.findById(request.getInternshipId())
                .orElseThrow(()->new RuntimeException("Internship not found!!"));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(()->new RuntimeException("Student not found!!"));

        evaluation.setCreatedAt(LocalDate.now());
        evaluation.setInternship(internship);
        evaluation.setStudent(student);

        return evaluationRepository.save(evaluation);
    }

    public Evaluation getEvaluation(Long id){
        return evaluationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Evaluation not found!!"));
    }

    @Transactional
    public Evaluation updateEvaluation(EvaluationRequestDto requestDto, Long id){

        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Evaluation not found!!"));

        Internship internship = internshipRepository.findById(requestDto.getInternshipId())
                .orElseThrow(()->new RuntimeException("Internship not found!!"));

        Student student = studentRepository.findById(requestDto.getStudentId())
                .orElseThrow(()->new RuntimeException("Student not found!!"));

        evaluation.setStudent(student);
        evaluation.setInternship(internship);
        evaluation.setCreatedAt(LocalDate.now());
        evaluation.setComment(requestDto.getComment());
        evaluation.setCommunication(requestDto.getCommunication());
        evaluation.setResponsibility(requestDto.getResponsibility());
        evaluation.setTeamwork(requestDto.getTeamwork());
        evaluation.setOverallRating(requestDto.getOverallRating());
        evaluation.setTechnicalSkills(requestDto.getTechnicalSkills());

        return evaluationRepository.save(evaluation);
    }

    @Transactional
    public void deleteEvaluation(Long id){
        evaluationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Evaluation not found!!"));

        evaluationRepository.deleteById(id);
    }
}
