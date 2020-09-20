package com.doan.cnpm.service.dto;

import org.springframework.stereotype.Service;

@Service
public class CourseDTO {

    private Long id;

    private Long idStudent;

    private Long idSubject;

    private Long level;

    private Long basicTuition;

    private Long idTutor;

    private Long idSchedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    public Long getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Long idSubject) {
        this.idSubject = idSubject;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getBasicTuition() {
        return basicTuition;
    }

    public void setBasicTuition(Long basicTuition) {
        this.basicTuition = basicTuition;
    }

    public Long getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Long idTutor) {
        this.idTutor = idTutor;
    }

    public Long getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Long idSchedule) {
        this.idSchedule = idSchedule;
    }

}
