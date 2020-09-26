package com.doan.cnpm.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SubjectDTO {
    @NotNull
    @NotBlank
    private String nameSubject;


    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

}
