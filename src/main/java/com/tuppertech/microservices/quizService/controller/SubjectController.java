package com.tuppertech.microservices.quizService.controller;

import com.tuppertech.microservices.quizService.request.SubjectRequest;
import com.tuppertech.microservices.quizService.request.TopicRequest;
import com.tuppertech.microservices.quizService.request.TopicUpdateRequest;
import com.tuppertech.microservices.quizService.response.SubjectResponse;
import com.tuppertech.microservices.quizService.response.TopicResponse;
import com.tuppertech.microservices.quizService.service.SubjectService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/subject")
@Log4j2
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @PostMapping("/createSubject")
    public ResponseEntity<SubjectResponse> createSubject(@RequestBody SubjectRequest subjectRequest){
        ResponseEntity<SubjectResponse> response;
        try{
            if(validateRequestObj(subjectRequest)) {
                subjectRequest.validate();
                SubjectResponse subjectResponse = subjectService.createSubject(subjectRequest);
                if(subjectResponse == null){
                    response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }else{
                    response = new ResponseEntity<>(subjectResponse,HttpStatus.CREATED);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: SubjectController :: createSubject :",e);
        }
        return response;
    }

    @GetMapping("/getSubject/{id}")
    public ResponseEntity<SubjectResponse> getSubject(@PathVariable String id){
        ResponseEntity<SubjectResponse> response;
        try{
            if(validateRequestObj(id)) {
                SubjectResponse subjectResponse = subjectService.getSubject(id);
                if(subjectResponse == null){
                    response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }else{
                    response = new ResponseEntity<>(subjectResponse,HttpStatus.OK);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: SubjectController :: getSubject :",e);
        }
        return response;
    }

    @GetMapping("/getAllSubjects")
    public ResponseEntity<Set<SubjectResponse>> getAllSubjects(){
        ResponseEntity<Set<SubjectResponse>> response;
        try{
            Set<SubjectResponse> subjectResponse = subjectService.getAllSubjects();
            if(subjectResponse == null){
                response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                response = new ResponseEntity<>(subjectResponse,HttpStatus.OK);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: SubjectController :: getAllSubjects :",e);
        }
        return response;
    }

    @PutMapping("/updateSubject/{id}")
    public ResponseEntity<SubjectResponse> updateSubject(@PathVariable String id,@RequestBody SubjectRequest subjectRequest){
        ResponseEntity<SubjectResponse> response;
        try{
            if(validateRequestObj(id)) {
                SubjectResponse subjectResponse = subjectService.updateSubject(id,subjectRequest);
                if(subjectResponse == null){
                    response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }else{
                    response = new ResponseEntity<>(subjectResponse,HttpStatus.ACCEPTED);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: SubjectController :: updateSubject :",e);
        }
        return response;
    }

    @DeleteMapping("/deleteSubject/{id}")
    public ResponseEntity<SubjectResponse> deleteSubject(@PathVariable String id){
        ResponseEntity<SubjectResponse> response;
        try{
            if(validateRequestObj(id)) {
                SubjectResponse subjectResponse = subjectService.deleteSubject(id);
                if(subjectResponse == null){
                    response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }else{
                    response = new ResponseEntity<>(subjectResponse,HttpStatus.OK);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: SubjectController :: deleteSubject :",e);
        }
        return response;
    }

    private boolean validateRequestObj(Object obj) {
        return obj!=null;
    }

    @PostMapping("/createTopic")
    public ResponseEntity<TopicResponse> createTopic(@RequestBody TopicRequest topicRequest){
        ResponseEntity<TopicResponse> response;
        try{
            if(validateRequestObj(topicRequest)) {
                topicRequest.validate();
                TopicResponse topicResponse = subjectService.createTopic(topicRequest);
                if(topicResponse == null){
                    response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }else{
                    response = new ResponseEntity<>(topicResponse,HttpStatus.CREATED);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: SubjectController :: createTopic :",e);
        }
        return response;
    }

    @PutMapping("/updateTopic")
    public ResponseEntity<TopicResponse> updateTopic(@RequestBody TopicUpdateRequest topicRequest){
        ResponseEntity<TopicResponse> response;
        try{
            if(validateRequestObj(topicRequest)) {
                topicRequest.validate();
                TopicResponse topicResponse = subjectService.updateTopic(topicRequest);
                if(topicResponse == null){
                    response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }else{
                    response = new ResponseEntity<>(topicResponse,HttpStatus.ACCEPTED);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: SubjectController :: updateTopic :",e);
        }
        return response;
    }

    @GetMapping("/getTopic/{id}")
    public ResponseEntity<TopicResponse> getTopic(@PathVariable Long id){
        ResponseEntity<TopicResponse> response;
        try{
            if(validateRequestObj(id)) {
                TopicResponse topicResponse = subjectService.getTopic(id);
                if(topicResponse == null){
                    response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }else{
                    response = new ResponseEntity<>(topicResponse,HttpStatus.OK);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: SubjectController :: getTopic :",e);
        }
        return response;
    }

    @GetMapping("/getAllTopicsOfSubject/{subjectId}")
    public ResponseEntity<List<TopicResponse>> getAllTopicsOfSubject(@PathVariable Long subjectId){
        ResponseEntity<List<TopicResponse>> response;
        try{
            if(validateRequestObj(subjectId)) {
                List<TopicResponse> topicResponse = subjectService.getAllTopicsOfSubject(subjectId);
                if(topicResponse == null){
                    response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }else{
                    response = new ResponseEntity<>(topicResponse,HttpStatus.OK);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: SubjectController :: getAllTopicsOfSubject :",e);
        }
        return response;
    }

    @DeleteMapping("/deleteTopic/{id}")
    public ResponseEntity<TopicResponse> deleteTopic(@PathVariable Long id){
        ResponseEntity<TopicResponse> response;
        try{
            if(validateRequestObj(id)) {
                TopicResponse topicResponse = subjectService.deleteTopic(id);
                if(topicResponse == null){
                    response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }else{
                    response = new ResponseEntity<>(topicResponse,HttpStatus.OK);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            log.error("Exception occurred :: SubjectController :: deleteTopic :",e);
        }
        return response;
    }
}
