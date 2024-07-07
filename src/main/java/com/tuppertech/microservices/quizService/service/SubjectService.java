package com.tuppertech.microservices.quizService.service;

import com.tuppertech.microservices.quizService.model.Topic;
import com.tuppertech.microservices.quizService.request.SubjectRequest;
import com.tuppertech.microservices.quizService.request.TopicRequest;
import com.tuppertech.microservices.quizService.request.TopicUpdateRequest;
import com.tuppertech.microservices.quizService.response.SubjectResponse;
import com.tuppertech.microservices.quizService.response.TopicResponse;

import java.util.List;
import java.util.Set;

public interface SubjectService {
    SubjectResponse createSubject(SubjectRequest subjectRequest);

    SubjectResponse getSubject(String id);

    Set<SubjectResponse> getAllSubjects();

    SubjectResponse deleteSubject(String id);

    SubjectResponse updateSubject(String id,SubjectRequest subjectRequest);

    TopicResponse createTopic(TopicRequest topicRequest);

    TopicResponse updateTopic(TopicUpdateRequest topicRequest);

    TopicResponse getTopic(Long id);

    List<TopicResponse> getAllTopicsOfSubject(Long subjectId);

    TopicResponse deleteTopic(Long id);
}
