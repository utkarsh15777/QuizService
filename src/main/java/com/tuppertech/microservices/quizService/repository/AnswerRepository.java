package com.tuppertech.microservices.quizService.repository;

import com.tuppertech.microservices.quizService.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
}
