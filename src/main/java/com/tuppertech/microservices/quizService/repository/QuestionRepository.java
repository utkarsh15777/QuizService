package com.tuppertech.microservices.quizService.repository;

import com.tuppertech.microservices.quizService.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
