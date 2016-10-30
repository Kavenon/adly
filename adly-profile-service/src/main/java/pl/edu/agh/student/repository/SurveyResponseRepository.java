package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.SurveyResponse;
import pl.edu.agh.student.model.SurveyResponseKey;

@Repository
public interface SurveyResponseRepository extends CrudRepository<SurveyResponse, SurveyResponseKey> {


}
