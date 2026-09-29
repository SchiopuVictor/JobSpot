package com.jobspot.mapper;

import com.jobspot.dto.StudentRequestDto;
import com.jobspot.dto.StudentResponseDto;
import com.jobspot.entity.Student;

public class StudentMapper {

    public static StudentResponseDto toDto(Student student){
        return StudentResponseDto.builder()
                .user_id(student.getUser().getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .city(student.getCity())
                .description(student.getDescription())
                .faculty(student.getFaculty())
                .studyYear(student.getStudyYear())
                .university(student.getUniversity())
                .build();
    }

    public static Student toEntity(StudentRequestDto request){
        return Student.builder()
                .phone(request.getPhone())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .city(request.getCity())
                .description(request.getDescription())
                .faculty(request.getFaculty())
                .university(request.getUniversity())
                .studyYear(request.getStudyYear())
                .build();

    }

}
