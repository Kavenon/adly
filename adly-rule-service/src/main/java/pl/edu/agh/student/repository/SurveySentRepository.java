package pl.edu.agh.student.repository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.rule.SurveySent;
import pl.edu.agh.student.model.rule.SurveySentKey;

import java.util.Date;
import java.util.UUID;

@Repository
public interface SurveySentRepository extends CrudRepository<SurveySent, SurveySentKey> {

    @Query("select count(*) from survey_sent where profile_id = ?0 and survey_id = ?1")
    int findByDeviceUuidAndSurveyId(UUID profileUuid, Integer surveyId);

    @Query("select count(*) from survey_sent where profile_id = ?0 and survey_id = ?1 and date > ?2")
    int findByDeviceUuidAndSurveyIdAndSince(UUID deviceUuid, Integer surveyId, Date since);
}
