package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.survey.PredefinedSurvey;

@Repository
public interface PredefinedSurveyRepository extends CrudRepository<PredefinedSurvey, Integer> {

}
