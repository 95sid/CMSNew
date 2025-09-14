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
@Table(name="Subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,length = 30)
    private String title;

    @ManyToOne
    @JoinColumn(nullable=false,name="professor_id")
    private Professor professor;

    @ManyToMany(mappedBy="subjects")
    private List<Student> students = new ArrayList<>();
}
