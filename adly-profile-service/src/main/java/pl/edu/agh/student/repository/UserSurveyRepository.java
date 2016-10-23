package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.survey.Survey;

import java.util.List;

@Repository
public interface UserSurveyRepository extends CrudRepository<Survey, Integer> {

    List<Survey> findByUserIdAndDeleted(Long userId, boolean deleted);

    Survey findByIdAndUserId(Integer id, Long userId);

    List<Survey> findByUserIdIsNull();

}
