package com.doan.cnpm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name ="subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_subject")
    private String nameSubject;

    @ManyToMany(mappedBy = "subject", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<TutorDetails> tutorDetails = new HashSet<>();


    public Long getId() {
        return id;
    }

    public Set<TutorDetails> getTutorDetails() {
        return tutorDetails;
    }

    public void setTutorDetails(Set<TutorDetails> tutorDetails) {
        this.tutorDetails = tutorDetails;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }
}
