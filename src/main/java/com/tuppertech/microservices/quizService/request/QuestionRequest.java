package com.tuppertech.microservices.quizService.request;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
public class QuestionRequest {
    private String name;
    private Set<OptionRequest> optionRequestSet;
}
