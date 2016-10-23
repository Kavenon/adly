package pl.edu.agh.student.services.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.student.model.survey.PredefinedSurvey;
import pl.edu.agh.student.repository.PredefinedSurveyRepository;

import java.util.List;

@Component
@Transactional
public class PredefinedSurveyService {

    private PredefinedSurveyRepository repository;

    @Autowired
    public PredefinedSurveyService(PredefinedSurveyRepository repository) {
        this.repository = repository;
    }

    public List<PredefinedSurvey> findAll() {
        return (List<PredefinedSurvey>) repository.findAll();
    }

}
