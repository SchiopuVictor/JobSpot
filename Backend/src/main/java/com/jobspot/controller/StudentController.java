package com.jobspot.controller;

import com.jobspot.dto.StudentRequestDto;
import com.jobspot.dto.StudentResponseDto;
import com.jobspot.entity.Student;
import com.jobspot.mapper.StudentMapper;
import com.jobspot.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudent(@PathVariable Long id){
        return ResponseEntity.ok(StudentMapper.toDto(studentService.getStudent(id)));
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto request){
        return ResponseEntity.ok(StudentMapper.toDto(studentService.createStudent(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @Valid @RequestBody StudentRequestDto request,
            @PathVariable Long id){
        return ResponseEntity.ok(StudentMapper.toDto(studentService.updateStudent(request, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
