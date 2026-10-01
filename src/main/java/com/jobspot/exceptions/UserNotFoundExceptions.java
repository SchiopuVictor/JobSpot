package com.jobspot.exceptions;

public class UserNotFoundExceptions extends RuntimeException {
    public UserNotFoundExceptions() {
        super("User not found!!");
    }
}
