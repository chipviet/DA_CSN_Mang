package com.doan.cnpm.service.exception;

public class NeedNotFoundException extends  RuntimeException{

    private static final long serialVersionUID = 1L;

    public NeedNotFoundException() {
        super("Need not found");
    }
}
