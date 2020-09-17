package com.doan.cnpm.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TutorDetailsDTO {
    @NotNull
    @NotBlank
    private String literacy;
    @NotNull
    @NotBlank
    private Long efficency;

    @NotNull
    @NotBlank
    private Long id_Subject;
    @NotBlank
    private String username;

    public String getLiteracy() {
        return literacy;
    }

    public void setLiteracy(String literacy) {
        this.literacy = literacy;
    }

    public Long getEfficency() {
        return efficency;
    }

    public void setEfficency(Long efficency) {
        this.efficency = efficency;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Long getId_Subject() {
        return id_Subject;
    }

    public void setId_Subject(Long id_Subject) {
        this.id_Subject = id_Subject;
    }

}
