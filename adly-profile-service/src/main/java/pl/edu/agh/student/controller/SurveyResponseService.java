package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.hibernate.JacksonUtil;
import pl.edu.agh.student.model.SurveyResponse;
import pl.edu.agh.student.model.SurveyResponseKey;
import pl.edu.agh.student.model.survey.SurveyResponseRequest;
import pl.edu.agh.student.repository.SurveyResponseRepository;
import pl.edu.agh.student.services.ProfileService;

import java.util.Date;
import java.util.UUID;

@Component
public class SurveyResponseService {

    private SurveyResponseRepository surveyResponseRepository;
    private ProfileService profileService;

    @Autowired
    public SurveyResponseService(SurveyResponseRepository surveyResponseRepository, ProfileService profileService) {
        this.surveyResponseRepository = surveyResponseRepository;
        this.profileService = profileService;
    }

    public void handleResponse(SurveyResponseRequest responseRequest) {

        SurveyResponseKey surveyResponseKey = new SurveyResponseKey(
                UUID.fromString(profileService.getProfileId(responseRequest.getUuid())),
                new Date(),
                responseRequest.getSurveyId()
        );

        SurveyResponse surveyResponse =
                new SurveyResponse(
                        surveyResponseKey,
                        JacksonUtil.toString(responseRequest),
                        UUID.fromString(responseRequest.getUuid()));

        surveyResponseRepository.save(surveyResponse);

    }

}
