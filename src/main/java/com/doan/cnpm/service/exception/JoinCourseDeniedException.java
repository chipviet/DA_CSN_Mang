package com.doan.cnpm.service.exception;

public class JoinCourseDeniedException extends  RuntimeException{

    private static final long serialVersionUID = 1L;

    public JoinCourseDeniedException() {
        super("Need not found");
    }
}
