package pl.edu.agh.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.exceptions.UnauthorizedAccessException;
import pl.edu.agh.student.model.Survey;
import pl.edu.agh.student.repository.SurveyRepository;

import java.util.List;
import java.util.Objects;

@Component
public class SurveyService {

    private SurveyRepository surveyRepository;
    private UserService userService;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository, UserService userService) {
        this.surveyRepository = surveyRepository;
        this.userService = userService;
    }

    public List<Survey> getSurveysForUserId(Long userId) {

        if(!Objects.equals(userService.getUserId(), userId)){
            throw new UnauthorizedAccessException();
        }

        return surveyRepository.findByUserId(userId);

    }

}
