package com.tuppertech.microservices.quizService.service;

import com.tuppertech.microservices.quizService.request.QuizRequest;
import com.tuppertech.microservices.quizService.request.ResultRequest;
import com.tuppertech.microservices.quizService.response.QuizResponse;
import com.tuppertech.microservices.quizService.response.ResultResponse;

import java.util.Set;

public interface QuizService {
    QuizResponse createQuiz(QuizRequest quizRequest);

    QuizResponse getQuiz(Long id);

    Set<QuizResponse> getTopicQuizs(Long id);

    QuizResponse deleteQuiz(Long id);

    ResultResponse getResult(ResultRequest resultRequest);
}
