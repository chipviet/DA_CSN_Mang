package com.doan.cnpm.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="tutor_details")
public class TutorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "literacy")
    private String literacy;

    @Column(name = "efficency")
    private Long efficency;


}
