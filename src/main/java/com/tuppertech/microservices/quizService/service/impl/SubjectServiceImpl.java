package com.tuppertech.microservices.quizService.service.impl;

import com.tuppertech.microservices.quizService.model.Subject;
import com.tuppertech.microservices.quizService.model.Topic;
import com.tuppertech.microservices.quizService.repository.SubjectRepository;
import com.tuppertech.microservices.quizService.repository.TopicRepository;
import com.tuppertech.microservices.quizService.request.SubjectRequest;
import com.tuppertech.microservices.quizService.request.TopicRequest;
import com.tuppertech.microservices.quizService.request.TopicUpdateRequest;
import com.tuppertech.microservices.quizService.response.SubjectResponse;
import com.tuppertech.microservices.quizService.response.TopicResponse;
import com.tuppertech.microservices.quizService.service.SubjectService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Log4j2
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, TopicRepository topicRepository) {
        this.subjectRepository = subjectRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public SubjectResponse createSubject(SubjectRequest subjectRequest) {
        SubjectResponse response=null;
        try{
            response = new SubjectResponse();
            Subject subject = new Subject();
            subject.setName(subjectRequest.getName());
            subject.setCreatedOn(LocalDate.now());
            subjectRepository.save(subject);
            response.setName(subject.getName());
            response.setCreatedOn(subject.getCreatedOn());
            response.setId(subject.getId());
            response.setTopicSet(subject.getTopicSet());
            response.setMessage("Created Successfully");
            return response;
        }catch(Exception e){
            log.error("SubjectServiceImpl :: createSubject :: error :",e);
        }
        return response;
    }

    @Override
    public SubjectResponse getSubject(String id) {
        SubjectResponse response=null;
        try{
            Optional<Subject> subjectFromDb = subjectRepository.findById(Long.parseLong(id));
            if(subjectFromDb.isPresent()){
                Subject subject = subjectFromDb.get();
                response = new SubjectResponse();
                response.setId(subject.getId());
                response.setName(subject.getName());
                response.setCreatedOn(subject.getCreatedOn());
                response.setTopicSet(subject.getTopicSet());
            }
        }catch(Exception e){
            log.error("SubjectServiceImpl :: getSubject :: error :",e);
        }
        return response;
    }

    @Override
    public Set<SubjectResponse> getAllSubjects() {
        Set<SubjectResponse> response=null;
        try{
            List<Subject> subjectList = subjectRepository.findAll();
            response = new HashSet<>();
            for(Subject subject : subjectList){
                SubjectResponse subjectResponse = new SubjectResponse();
                subjectResponse.setId(subject.getId());
                subjectResponse.setName(subject.getName());
                subjectResponse.setCreatedOn(subject.getCreatedOn());
                subjectResponse.setTopicSet(subject.getTopicSet());
                response.add(subjectResponse);
            }
            log.debug("getAllSubjects::size: {}",response.size());
        }catch(Exception e){
            log.error("SubjectServiceImpl :: getAllSubjects :: error :",e);
        }
        return response;
    }

    @Override
    public SubjectResponse deleteSubject(String id) {
        SubjectResponse response=null;
        try{
            Optional<Subject> subjectFromDb = subjectRepository.findById(Long.parseLong(id));
            if(subjectFromDb.isPresent()){
                Subject subject = subjectFromDb.get();
                subjectRepository.delete(subject);
                response = new SubjectResponse();
                response.setId(subject.getId());
                response.setName(subject.getName());
                response.setCreatedOn(subject.getCreatedOn());
                response.setTopicSet(subject.getTopicSet());
                response.setMessage("Deleted Successfully");
            }
        }catch(Exception e){
            log.error("SubjectServiceImpl :: deleteSubject :: error :",e);
        }
        return response;
    }

    @Override
    public SubjectResponse updateSubject(String id,SubjectRequest subjectRequest) {
        SubjectResponse response=null;
        try{
            Optional<Subject> subjectFromDb = subjectRepository.findById(Long.parseLong(id));
            if(subjectFromDb.isPresent()){
                Subject subject = subjectFromDb.get();
                subject.setName(subjectRequest.getName());
                subjectRepository.save(subject);
                response = new SubjectResponse();
                response.setName(subject.getName());
                response.setId(subject.getId());
                response.setMessage("Updated Successfully");
            }
        }catch(Exception e){
            log.error("SubjectServiceImpl :: deleteSubject :: error :",e);
        }
        return response;
    }

    @Override
    public TopicResponse createTopic(TopicRequest topicRequest) {
        TopicResponse response=null;
        try{
            Optional<Subject> subjectFromDb = subjectRepository.findById(topicRequest.getSubjectId());
            if(subjectFromDb.isPresent()){
                Subject subject = subjectFromDb.get();
                Topic topic = new Topic();
                topic.setName(topicRequest.getTopicName());
                topic.setCreatedOn(LocalDate.now());
                topic.setSubject(subject);
                subject.getTopicSet().add(topic);
                Topic topicSavedToDB = topicRepository.save(topic);
                subjectRepository.save(subject);
                response = new TopicResponse();
                response.setTopicName(topicSavedToDB.getName());
                response.setSubjectName(topicSavedToDB.getSubject().getName());
                response.setCreatedDate(topicSavedToDB.getCreatedOn());
                response.setId(topicSavedToDB.getId());
                response.setMessage("Created Successfully");
            }
        }catch(Exception e){
            log.error("SubjectServiceImpl :: createTopic :: error :",e);
        }
        return response;
    }

    @Override
    public TopicResponse updateTopic(TopicUpdateRequest topicRequest) {
        TopicResponse response=null;
        try{
            Optional<Topic> topicFromDB = topicRepository.findById(topicRequest.getTopicId());
            if(topicFromDB.isPresent()){
                Topic topic = topicFromDB.get();
                topic.setName(topicRequest.getUpdatedTopicName());
                topicRepository.save(topic);
                response = new TopicResponse();
                response.setTopicName(topic.getName());
                response.setSubjectName(topic.getSubject().getName());
                response.setMessage("Updated Successfully");
            }
        }catch(Exception e){
            log.error("SubjectServiceImpl :: updateTopic :: error :",e);
        }
        return response;
    }

    @Override
    public TopicResponse getTopic(Long id) {
        TopicResponse response=null;
        try{
            Optional<Topic> topicFromDB = topicRepository.findById(id);
            if(topicFromDB.isPresent()){
                Topic topic = topicFromDB.get();
                response = new TopicResponse();
                response.setTopicName(topic.getName());
                response.setSubjectName(topic.getSubject().getName());
            }
        }catch(Exception e){
            log.error("SubjectServiceImpl :: getTopic :: error :",e);
        }
        return response;
    }

    @Override
    public List<TopicResponse> getAllTopicsOfSubject(Long subjectId) {
        List<TopicResponse> response=null;
        try{
            Optional<Subject> subjectFromDB = subjectRepository.findById(subjectId);
            if(subjectFromDB.isPresent()){
                Subject subject = subjectFromDB.get();
                response = new ArrayList<>();
                for(Topic topic : subject.getTopicSet()){
                    TopicResponse topicResponse = new TopicResponse();
                    topicResponse.setTopicName(topic.getName());
                    topicResponse.setSubjectName(topic.getSubject().getName());
                    response.add(topicResponse);
                }
            }
        }catch(Exception e){
            log.error("SubjectServiceImpl :: getAllTopicsOfSubject :: error :",e);
        }
        return response;
    }

    @Override
    public TopicResponse deleteTopic(Long id) {
        TopicResponse response=null;
        try{
            Optional<Topic> topicFromDB = topicRepository.findById(id);
            if(topicFromDB.isPresent()){
                Topic topic = topicFromDB.get();
                topicRepository.deleteById(id);
                response = new TopicResponse();
                response.setTopicName(topic.getName());
                response.setSubjectName(topic.getSubject().getName());
                response.setMessage("Deleted Successfully");
            }
        }catch(Exception e){
            log.error("SubjectServiceImpl :: deleteTopic :: error :",e);
        }
        return response;
    }
}
