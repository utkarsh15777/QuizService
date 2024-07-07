package com.tuppertech.microservices.quizService.repository;

import com.tuppertech.microservices.quizService.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
