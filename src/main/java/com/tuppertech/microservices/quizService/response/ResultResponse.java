package com.tuppertech.microservices.quizService.response;

import lombok.Data;

@Data
public class ResultResponse {
    private Long quizId;
    private Integer score;
}
