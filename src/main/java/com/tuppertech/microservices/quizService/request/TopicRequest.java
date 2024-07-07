package com.tuppertech.microservices.quizService.request;

import com.tuppertech.microservices.quizService.exception.ValidationException;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

@Data
public class TopicRequest {
    private String topicName;
    private Long subjectId;

    public void validate() throws ValidationException {
        if(StringUtils.isEmpty(topicName)){
            throw new ValidationException("Name Can't be empty");
        }
        if(ObjectUtils.isEmpty(subjectId)){
            throw new ValidationException("Subject Id Can't be empty");
        }
    }
}
