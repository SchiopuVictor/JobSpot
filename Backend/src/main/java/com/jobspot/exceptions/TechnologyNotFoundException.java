package com.jobspot.exceptions;

public class TechnologyNotFoundException extends RuntimeException {
    public TechnologyNotFoundException() {
        super("Technology not found!!");
    }
}
