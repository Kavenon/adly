package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.student.services.SurveyService;

@RestController
public class SurveyController {

    private SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @RequestMapping(value = "/accounts/{accountNumber}/surveys", method = RequestMethod.GET)
    public ResponseEntity getSurveysForUser(@PathVariable("accountNumber") Long accountNumber){
        return new ResponseEntity<>(surveyService.getSurveysForUserId(accountNumber), HttpStatus.OK);
    }

}
