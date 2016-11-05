package pl.edu.agh.student.repository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.rule.SurveySent;
import pl.edu.agh.student.model.rule.SurveySentKey;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface SurveySentRepository extends CrudRepository<SurveySent, SurveySentKey> {

    @Query("select * from survey_sent where profile_id = ?0 and survey_id = ?1")
    List<SurveySent> findByDeviceUuidAndSurveyId(UUID profileUuid, Integer surveyId);

    @Query("select * from survey_sent where profile_id = ?0 and survey_id = ?1 and date > ?2")
    List<SurveySent> findByDeviceUuidAndSurveyIdAndSince(UUID deviceUuid, Integer surveyId, Date since);
}
