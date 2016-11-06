package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.student.services.SurveyClientService;

@RestController
@RequestMapping("/survey/client")
public class SurveyClientController {

    private SurveyClientService service;

    @Autowired
    public SurveyClientController(SurveyClientService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getClientSurvey(@RequestParam("id") Integer surveyId) {
        return new ResponseEntity<>(service.getClientSurvey(surveyId), HttpStatus.OK);
    }

}