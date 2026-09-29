package com.jobspot.service;

import com.jobspot.dto.StudentRequestDto;
import com.jobspot.entity.Student;
import com.jobspot.entity.User;
import com.jobspot.mapper.StudentMapper;
import com.jobspot.repository.StudentRepository;
import com.jobspot.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Student createStudent(StudentRequestDto request){
        User user = userRepository.findById(request
                .getUser_id()).orElseThrow(()->new RuntimeException("user not found!!"));

        Student student = StudentMapper.toEntity(request);

        student.setUser(user);
        return  studentRepository.save(student);
    }

    public Student getStudent(Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Student not found!!"));
        userRepository.findById(student.getUser().getId())
                .orElseThrow(()->new RuntimeException("User not found!!"));

        return student;
    }

    @Transactional
    public Student updateStudent(StudentRequestDto request, Long id){

        Student student = studentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Student not found!!"));

        User user = userRepository.findById(student.getUser().getId())
                .orElseThrow(()->new RuntimeException("User not found!!"));

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setCity(request.getCity());
        student.setDescription(request.getDescription());
        student.setFaculty(request.getFaculty());
        student.setPhone(request.getPhone());
        student.setUniversity(request.getUniversity());
        student.setStudyYear(request.getStudyYear());
        student.setUser(user);

        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long id){
        studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student not found!!"));
        studentRepository.deleteById(id);
    }

}
