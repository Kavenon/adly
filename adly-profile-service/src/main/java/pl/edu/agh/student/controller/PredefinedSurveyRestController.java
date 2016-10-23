package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.student.model.survey.Survey;
import pl.edu.agh.student.services.survey.PredefinedSurveyService;

import java.util.List;

@RestController
@RequestMapping("/profile/predefined-survey")
public class PredefinedSurveyRestController {

    private PredefinedSurveyService service;

    @Autowired
    public PredefinedSurveyRestController(PredefinedSurveyService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Survey> getProperties() {
        return service.findAllPredefined();
    }

}