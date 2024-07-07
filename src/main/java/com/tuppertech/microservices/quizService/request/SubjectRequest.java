package com.tuppertech.microservices.quizService.request;

import com.tuppertech.microservices.quizService.exception.ValidationException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class SubjectRequest {
    private String name;
    public void validate() throws ValidationException {
        if(StringUtils.isEmpty(name)){
            throw new ValidationException("Name Can't be empty");
        }
    }
}
