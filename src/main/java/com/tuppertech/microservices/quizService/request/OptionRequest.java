package com.tuppertech.microservices.quizService.request;

import lombok.Data;

@Data
public class OptionRequest {
    private String value;
    private Boolean isAnswer;
}
