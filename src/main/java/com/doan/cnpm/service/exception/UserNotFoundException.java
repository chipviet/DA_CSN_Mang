package com.doan.cnpm.service.exception;

public class UserNotFoundException  extends  RuntimeException{

    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super("Username or password is invalid");
    }
}