package com.tuppertech.microservices.quizService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_option_id", referencedColumnName = "id")
    private Option option;
    @OneToOne(mappedBy = "answer")
    private Question question;
}
