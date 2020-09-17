package com.doan.cnpm.service.exception;

public class TutorNotFoundException  extends  RuntimeException{

    private static final long serialVersionUID = 1L;

    public TutorNotFoundException() {
        super("Tutor not found");
    }
}
