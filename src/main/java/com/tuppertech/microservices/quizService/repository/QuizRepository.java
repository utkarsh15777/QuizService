package com.tuppertech.microservices.quizService.repository;

import com.tuppertech.microservices.quizService.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
