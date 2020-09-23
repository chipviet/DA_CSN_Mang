package com.doan.cnpm.service.dto;

import org.springframework.stereotype.Service;

@Service
public class CourseDTO {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdNeed() {
        return idNeed;
    }

    public void setIdNeed(Long idNeed) {
        this.idNeed = idNeed;
    }

    public Long getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Long idTutor) {
        this.idTutor = idTutor;
    }

    private Long idNeed;

    private Long idTutor;

}
