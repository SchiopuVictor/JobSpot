package com.jobspot.exceptions;

public class InternshipNotFoundException extends RuntimeException {
    public InternshipNotFoundException() {
        super("Internship not found!!");
    }
}
