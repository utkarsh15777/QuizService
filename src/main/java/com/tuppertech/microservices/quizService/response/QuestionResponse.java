package com.tuppertech.microservices.quizService.response;

import lombok.Data;

import java.util.Set;

@Data
public class QuestionResponse {
    private Long id;
    private String name;
    private Set<OptionResponse> optionResponseSet;
    private Long answerOptionId;
}
