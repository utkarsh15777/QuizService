package com.tuppertech.microservices.quizService.controller;

import com.tuppertech.microservices.quizService.request.QuizRequest;
import com.tuppertech.microservices.quizService.request.ResultRequest;
import com.tuppertech.microservices.quizService.response.QuizResponse;
import com.tuppertech.microservices.quizService.response.ResultResponse;
import com.tuppertech.microservices.quizService.service.QuizService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/quiz")
@Log4j2
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/createQuiz")
    public ResponseEntity<QuizResponse> createSubject(@RequestBody QuizRequest quizRequest){
        ResponseEntity<QuizResponse> response;
        try{
            if(validateRequestObj(quizRequest)) {
                quizRequest.validate();
                QuizResponse quizResponse = quizService.createQuiz(quizRequest);
                if(quizResponse == null){
                    response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }else{
                    response = new ResponseEntity<>(quizResponse,HttpStatus.CREATED);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: QuizController :: createQuiz :",e);
        }
        return response;
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<QuizResponse> getQuiz(@PathVariable String id){
        ResponseEntity<QuizResponse> response;
        try{
            if(validateRequestObj(id)) {
                QuizResponse quizResponse = quizService.getQuiz(Long.parseLong(id));
                if(quizResponse == null){
                    response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }else{
                    response = new ResponseEntity<>(quizResponse,HttpStatus.OK);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: QuizController :: getQuiz :",e);
        }
        return response;
    }

    @GetMapping("/getTopicQuizs/{id}")
    public ResponseEntity<Set<QuizResponse>> getTopicQuizs(@PathVariable String id){
        ResponseEntity<Set<QuizResponse>> response;
        try{
            if(validateRequestObj(id)) {
                Set<QuizResponse> quizResponse = quizService.getTopicQuizs(Long.parseLong(id));
                if(quizResponse == null){
                    response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }else{
                    response = new ResponseEntity<>(quizResponse,HttpStatus.OK);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: QuizController :: getTopicQuizs :",e);
        }
        return response;
    }

    @DeleteMapping("/deleteQuiz/{id}")
    public ResponseEntity<QuizResponse> deleteQuiz(@PathVariable String id){
        ResponseEntity<QuizResponse> response;
        try{
            if(validateRequestObj(id)) {
                QuizResponse quizResponse = quizService.deleteQuiz(Long.parseLong(id));
                if(quizResponse == null){
                    response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }else{
                    response = new ResponseEntity<>(quizResponse,HttpStatus.ACCEPTED);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: QuizController :: deleteQuiz :",e);
        }
        return response;
    }

    @GetMapping("/getResult")
    public ResponseEntity<ResultResponse> getResult(@RequestBody ResultRequest resultRequest){
        ResponseEntity<ResultResponse> response;
        try{
            if(validateRequestObj(resultRequest)) {
                ResultResponse resultResponse = quizService.getResult(resultRequest);
                if(resultResponse == null){
                    response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }else{
                    response = new ResponseEntity<>(resultResponse,HttpStatus.OK);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: QuizController :: getResult :",e);
        }
        return response;
    }

    private boolean validateRequestObj(Object obj) {
        return obj!=null;
    }
}
