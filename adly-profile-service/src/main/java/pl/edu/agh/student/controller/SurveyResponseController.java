package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.student.model.survey.SurveyResponseRequest;
import pl.edu.agh.student.services.SurveyResponseService;

@RestController
@RequestMapping("/survey/response")
public class SurveyResponseController {

    private SurveyResponseService service;

    @Autowired
    public SurveyResponseController(SurveyResponseService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> handleResponse(@RequestBody SurveyResponseRequest responseRequest) {
        service.handleResponse(responseRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}