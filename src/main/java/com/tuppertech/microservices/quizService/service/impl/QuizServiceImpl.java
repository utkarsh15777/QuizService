package com.tuppertech.microservices.quizService.service.impl;

import com.tuppertech.microservices.quizService.model.*;
import com.tuppertech.microservices.quizService.repository.QuizRepository;
import com.tuppertech.microservices.quizService.repository.TopicRepository;
import com.tuppertech.microservices.quizService.request.OptionRequest;
import com.tuppertech.microservices.quizService.request.QuestionRequest;
import com.tuppertech.microservices.quizService.request.QuizRequest;
import com.tuppertech.microservices.quizService.request.ResultRequest;
import com.tuppertech.microservices.quizService.response.OptionResponse;
import com.tuppertech.microservices.quizService.response.QuestionResponse;
import com.tuppertech.microservices.quizService.response.QuizResponse;
import com.tuppertech.microservices.quizService.response.ResultResponse;
import com.tuppertech.microservices.quizService.service.QuizService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Log4j2
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository, TopicRepository topicRepository) {
        this.quizRepository = quizRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public QuizResponse createQuiz(QuizRequest quizRequest) {
        QuizResponse response = null;
        try {
            Optional<Topic> topicFromDB = topicRepository.findById(quizRequest.getTopicId());
            if(topicFromDB.isPresent()){
                Topic topic = topicFromDB.get();
                Quiz quiz=new Quiz();
                quiz.setName(quizRequest.getName());
                quiz.setCreatedOn(LocalDate.now());
                List<QuestionRequest> questionRequestSet = quizRequest.getQuestionSet();
                log.debug("questionRequestSet: {}",questionRequestSet.toString());
                for(QuestionRequest questionRequest: questionRequestSet){
                    Question question = new Question();
                    question.setName(questionRequest.getName());
                    Set<OptionRequest> optionRequestSet = questionRequest.getOptionRequestSet();
                    for(OptionRequest optionRequest: optionRequestSet){
                        Option option = new Option();
                        option.setName(optionRequest.getValue());
                        option.setQuestion(question);
                        if(optionRequest.getIsAnswer()){
                            Answer answer = new Answer();
                            answer.setOption(option);
                            answer.setQuestion(question);
                            question.setAnswer(answer);
                        }
                        question.getOptionSet().add(option);
                    }
                    question.setQuiz(quiz);
                    quiz.getQuestionSet().add(question);
                }
                quiz.setTopic(topic);
                if(topic.getQuizSet()==null){
                    topic.setQuizSet(new HashSet<>());
                }
                topic.getQuizSet().add(quiz);
                Quiz quizSavedToDB = quizRepository.save(quiz);
                topicRepository.save(topic);
                response = getQuiz(quizSavedToDB.getId());
            }

        }catch (Exception e){
            log.error("QuizServiceImpl :: createQuiz :: error :",e);
        }
        return response;
    }

    @Override
    public QuizResponse getQuiz(Long id) {
        QuizResponse quizResponse = null;
        try{
            Optional<Quiz> quizFromDB = quizRepository.findById(id);
            if(quizFromDB.isPresent()){
                Quiz quiz = quizFromDB.get();
                quizResponse = new QuizResponse();
                quizResponse.setTopicId(quiz.getTopic().getId());
                quizResponse.setTopicName(quiz.getTopic().getName());
                quizResponse.setQuizId(quiz.getId());
                quizResponse.setQuizName(quiz.getName());
                quizResponse.setQuestionResponseSet(new HashSet<>());
                Set<Question> questionSet = quiz.getQuestionSet();
                for(Question question: questionSet){
                    QuestionResponse questionResponse = new QuestionResponse();
                    questionResponse.setId(question.getId());
                    questionResponse.setName(question.getName());
                    questionResponse.setOptionResponseSet(new HashSet<>());
                    Set<Option> optionSet = question.getOptionSet();
                    for(Option option: optionSet){
                        OptionResponse optionResponse = new OptionResponse();
                        optionResponse.setId(option.getId());
                        optionResponse.setValue(option.getName());
                        questionResponse.getOptionResponseSet().add(optionResponse);
                    }
                    questionResponse.setAnswerOptionId(question.getAnswer().getOption().getId());
                    quizResponse.getQuestionResponseSet().add(questionResponse);
                }
            }
        }catch (Exception e){
            log.error("QuizServiceImpl :: getQuiz :: error :",e);
        }
        return quizResponse;
    }

    @Override
    public Set<QuizResponse> getTopicQuizs(Long id) {
        Set<QuizResponse> quizResponseSet = null;
        try{
            Optional<Topic> topicFromDB = topicRepository.findById(id);
            if(topicFromDB.isPresent()) {
                Topic topic = topicFromDB.get();
                quizResponseSet = new HashSet<>();
                for(Quiz quiz: topic.getQuizSet()){
                    quizResponseSet.add(getQuiz(quiz.getId()));
                }
            }
        }catch (Exception e){
            log.error("QuizServiceImpl :: getTopicQuizs :: error :",e);
        }
        return quizResponseSet;
    }

    @Override
    public QuizResponse deleteQuiz(Long id) {
        QuizResponse quizResponse = null;
        try{
            Optional<Quiz> quizFromDB = quizRepository.findById(id);
            if(quizFromDB.isPresent()) {
                Quiz quiz = quizFromDB.get();
                quizRepository.deleteById(id);
                quizResponse = new QuizResponse();
                quizResponse.setQuizId(quiz.getId());
                quizResponse.setQuizName(quiz.getName());
                quizResponse.setTopicName(quiz.getTopic().getName());
                quizResponse.setTopicId(quiz.getTopic().getId());
                quizResponse.setMessage("Successfully deleted");
            }
        }catch (Exception e){
            log.error("QuizServiceImpl :: deleteQuiz :: error :",e);
        }
        return quizResponse;
    }

    @Override
    public ResultResponse getResult(ResultRequest resultRequest) {
        ResultResponse resultResponse = null;
        try{
            Optional<Quiz> quizFromDB = quizRepository.findById(resultRequest.getQuizId());
            if(quizFromDB.isPresent()) {
                Quiz quiz = quizFromDB.get();
                Set<Question> questionSet = quiz.getQuestionSet();
                int score=0;
                for(Question question: questionSet){
                    Long correctAnswer = question.getAnswer().getOption().getId();
                    Long candidateAnswer = resultRequest.getQuestionAnswerMap().get(question.getId());
                    if(Objects.equals(correctAnswer, candidateAnswer)){
                        score++;
                    }
                }
                resultResponse = new ResultResponse();
                resultResponse.setQuizId(quiz.getId());
                resultResponse.setScore(score);
            }
        }catch (Exception e){
            log.error("QuizServiceImpl :: getResult :: error :",e);
        }
        return resultResponse;
    }
}
