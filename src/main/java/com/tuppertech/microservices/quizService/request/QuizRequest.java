package com.tuppertech.microservices.quizService.request;

import com.tuppertech.microservices.quizService.exception.ValidationException;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

@Data
public class QuizRequest {
    private Long topicId;
    private String name;
    private List<QuestionRequest> questionSet;
    public void validate() throws ValidationException {
        if(ObjectUtils.isEmpty(topicId)){
            throw new ValidationException("Topic Id can't be empty");
        }
        if(StringUtils.isEmpty(name)){
            throw new ValidationException("Name can't be empty");
        }
        if(CollectionUtils.isEmpty(questionSet)){
            throw new ValidationException("Please add question set");
        }
    }
}
