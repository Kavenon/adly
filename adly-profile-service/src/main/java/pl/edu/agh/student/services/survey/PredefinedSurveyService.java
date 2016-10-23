package pl.edu.agh.student.services.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.student.model.survey.Survey;
import pl.edu.agh.student.repository.UserSurveyRepository;

import java.util.List;

@Component
@Transactional
public class PredefinedSurveyService {

    private UserSurveyRepository repository;

    @Autowired
    public PredefinedSurveyService(UserSurveyRepository repository) {
        this.repository = repository;
    }

    public List<Survey> findAllPredefined() {
        return repository.findByUserIdIsNull();
    }
}
