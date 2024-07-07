package com.tuppertech.microservices.quizService.repository;

import com.tuppertech.microservices.quizService.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {
}
