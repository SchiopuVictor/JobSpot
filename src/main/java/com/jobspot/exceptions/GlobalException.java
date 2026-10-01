package com.jobspot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String,String>> globalExceptions(MethodArgumentNotValidException exception){
        HashMap<String,String> map = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) ->{
            map.put(error.getObjectName(),error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(map);
    }

    @ExceptionHandler(UserNotFoundExceptions.class)
    public ResponseEntity<Map<String,String>> userException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message","User not found!!"));
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Map<String,String>>studentException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message","Student not found!!"));
    }

    @ExceptionHandler(TechnologyNotFoundException.class)
    public ResponseEntity<Map<String,String>> technologyException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message","Technology not found!!"));
    }

    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<Map<String,String>> notificationException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message","Notification not found!!"));
    }

    @ExceptionHandler(InterviewNotFoundException.class)
    public ResponseEntity<Map<String,String>>interviewException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message","Interview not found!!"));
    }

    @ExceptionHandler(CvNotFoundException.class)
    public ResponseEntity<Map<String,String>>cvException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message","Cv not found!!"));
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<Map<String,String>>companyException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message","Company not found!!"));
    }

    @ExceptionHandler(InternshipNotFoundException.class)
    public ResponseEntity<Map<String,String>>internshipException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message","Internship not found!!"));
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<Map<String,String>>applicationException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message","Application not found!!"));
    }

}
