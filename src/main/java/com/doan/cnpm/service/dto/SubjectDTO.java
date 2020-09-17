package com.doan.cnpm.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SubjectDTO {
    @NotNull
    @NotBlank
    private String nameSubject;

    public Long getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Long idTutor) {
        this.idTutor = idTutor;
    }

    @NotNull
    @NotBlank
    private Long idTutor;

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

}
