package com.jobspot.exceptions;

public class EvaluationNotFoundException extends RuntimeException {
    public EvaluationNotFoundException() {
        super("Evaluation not found!!");
    }
}
