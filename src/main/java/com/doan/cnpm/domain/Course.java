package com.doan.cnpm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name ="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_need")
    private Long idNeed;

    @Column(name = "id_tutor")
    private Long idTutor;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "student_course",
            joinColumns = {@JoinColumn(name = "id_course")},
            inverseJoinColumns = {@JoinColumn(name = "id_student")}
    )
    private Set<User> student = new HashSet<>();

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

    public Set<User> getStudent() {
        return student;
    }

    public void setStudent(Set<User> student) {
        this.student = student;
    }


    public void addStudent(User student1)
    {
        this.student.add(student1);
        student1.getCourses().add(this);
    }

    public void removeStudent(User student1)
    {
        this.getStudent().remove(student1);
        student1.getCourses().remove(this);
    }

    public void removeStudent()
    {
        for (User student1 : new HashSet<>(student)){
            removeStudent(student1);
        }
    }
}
