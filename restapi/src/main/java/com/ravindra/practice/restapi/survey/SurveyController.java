package com.ravindra.practice.restapi.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping("/surveys/{surveyId}/questions")
    private List<Question> getQuestionsForSurvey(@PathVariable  String surveyId){
        return surveyService.retrieveQuestions(surveyId);
    }

    @GetMapping("/surveys/{surveyId}/questions/{questionId}")
    private Question getDetailsOfQuestionsForSurvey(@PathVariable  String surveyId,@PathVariable String questionId){
        return surveyService.retrieveQuestion(surveyId,questionId);
    }

    @PostMapping("/surveys/{surveyId}/questions")
    private ResponseEntity<Void> createQuestionsForSurvey(@PathVariable  String surveyId, @RequestBody Question question){
        Question responseQuestion = surveyService.addQuestion(surveyId, question);
        if(responseQuestion ==null){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(question.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
