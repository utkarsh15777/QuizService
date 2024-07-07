package com.tuppertech.microservices.quizService.repository;

import com.tuppertech.microservices.quizService.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option,Long> {
}
