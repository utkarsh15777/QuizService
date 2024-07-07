package com.tuppertech.microservices.quizService.response;

import lombok.Data;

import java.util.Set;

@Data
public class QuizResponse {
    private Long quizId;
    private Long topicId;
    private String quizName;
    private String topicName;
    private Set<QuestionResponse> questionResponseSet;
    private String message ="Success";
}
