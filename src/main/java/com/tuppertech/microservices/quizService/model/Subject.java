package com.tuppertech.microservices.quizService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate createdOn;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "subject",cascade = CascadeType.ALL)
    private Set<Topic> topicSet = new HashSet<>();
}
