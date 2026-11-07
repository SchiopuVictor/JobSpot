package com.jobspot.exceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("Student Not Found!!");
    }
}
