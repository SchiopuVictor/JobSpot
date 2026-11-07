package com.jobspot.exceptions;

public class InterviewNotFoundException extends RuntimeException {
    public InterviewNotFoundException() {
        super("Interview not found!!");
    }
}
