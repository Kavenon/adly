package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.survey.PredefinedSurvey;
import pl.edu.agh.student.model.survey.UserSurvey;

import java.util.List;

@Repository
public interface UserSurveyRepository extends CrudRepository<UserSurvey, Integer> {

    List<PredefinedSurvey> findByUserId(Long userId);

    List<UserSurvey> findByUserIdAndDeleted(Long userId, boolean deleted);

    UserSurvey findByIdAndUserId(Integer id, Long userId);

}
