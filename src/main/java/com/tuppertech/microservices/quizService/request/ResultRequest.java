package com.tuppertech.microservices.quizService.request;

import com.tuppertech.microservices.quizService.exception.ValidationException;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Data
public class ResultRequest {
    private Long quizId;
    private Map<Long,Long> questionAnswerMap;

    public void validate() throws ValidationException {
        if(ObjectUtils.isEmpty(quizId)){
            throw new ValidationException("Quiz Id can't be empty");
        }
        if(CollectionUtils.isEmpty(questionAnswerMap)){
            throw new ValidationException("Please provide question and responded answers");
        }
    }
}
