package com.jobspot.mapper;

import com.jobspot.dto.EvaluationRequestDto;
import com.jobspot.dto.EvaluationResponseDto;
import com.jobspot.entity.Evaluation;

public class EvaluationMapper {

    public static EvaluationResponseDto toDto(Evaluation evaluation){
        return EvaluationResponseDto.builder()
                .studentName(evaluation.getStudent().getFirstName()+" "+ evaluation.getStudent().getLastName())
                .internshipName(evaluation.getInternship().getTitle())
                .technicalSkills(evaluation.getTechnicalSkills())
                .communication(evaluation.getCommunication())
                .teamwork(evaluation.getTeamwork())
                .overallRating(evaluation.getOverallRating())
                .comment(evaluation.getComment())
                .createdAt(evaluation.getCreatedAt())
                .responsibility(evaluation.getResponsibility())
                .build();
    }

    public static Evaluation toEntity(EvaluationRequestDto request){
        return Evaluation.builder()
                .comment(request.getComment())
                .communication(request.getCommunication())
                .overallRating(request.getOverallRating())
                .responsibility(request.getResponsibility())
                .teamwork(request.getTeamwork())
                .technicalSkills(request.getTechnicalSkills())
                .build();
    }
}
