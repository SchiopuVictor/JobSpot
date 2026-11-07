package com.jobspot.exceptions;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException() {
        super("Application not found!!");
    }
}
