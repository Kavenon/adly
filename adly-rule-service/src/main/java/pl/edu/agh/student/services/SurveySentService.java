package pl.edu.agh.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.rule.SurveySent;
import pl.edu.agh.student.repository.SurveySentRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class SurveySentService {

    private SurveySentRepository surveySentRepository;

    @Autowired
    public SurveySentService(SurveySentRepository surveySentRepository) {
        this.surveySentRepository = surveySentRepository;
    }

    public boolean surveySent(UUID profileUuid, Integer surveyId){

        List<SurveySent> byDeviceUuidAndSurveyId = surveySentRepository.findByDeviceUuidAndSurveyId(profileUuid, surveyId);
        return byDeviceUuidAndSurveyId.size() > 0;

    }

    public boolean surveySent(UUID profileUuid, Integer surveyId, Date since){

        List<SurveySent> byDeviceUuidAndSurveyIdAndSince = surveySentRepository.findByDeviceUuidAndSurveyIdAndSince(profileUuid, surveyId, since);
        return byDeviceUuidAndSurveyIdAndSince.size() > 0;

    }
}
