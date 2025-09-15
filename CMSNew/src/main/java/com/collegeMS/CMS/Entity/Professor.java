package com.collegeMS.CMS.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,length =25)
    private String title;

    @OneToMany(mappedBy = "professor")
    private List<Subject> subjects = new ArrayList<>();

    @ManyToMany(mappedBy= "professors")
    private List<Student> students = new ArrayList<>();
}
