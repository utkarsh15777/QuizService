package com.tuppertech.microservices.quizService.response;

import com.tuppertech.microservices.quizService.model.Topic;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class SubjectResponse {
    private String name;
    private Long id;
    private LocalDate createdOn;
    private Set<Topic> topicSet;
    private String message = "Success";
}
