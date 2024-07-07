package com.tuppertech.microservices.quizService.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TopicResponse {
    private Long id;
    private LocalDate createdDate;
    private String topicName;
    private String subjectName;
    private String message = "Success";
}
