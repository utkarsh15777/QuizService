package com.tuppertech.microservices.quizService.request;

import com.tuppertech.microservices.quizService.exception.ValidationException;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

@Data
public class TopicUpdateRequest {
    private Long topicId;
    private String updatedTopicName;

    public void validate() throws ValidationException {
        if(StringUtils.isEmpty(updatedTopicName)){
            throw new ValidationException("Topic name Can't be empty");
        }
        if(ObjectUtils.isEmpty(topicId)){
            throw new ValidationException("Subject Id Can't be empty");
        }
    }
}
