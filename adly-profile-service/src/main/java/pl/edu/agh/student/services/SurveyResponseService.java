package pl.edu.agh.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.hibernate.JacksonUtil;
import pl.edu.agh.student.model.SurveyResponse;
import pl.edu.agh.student.model.SurveyResponseKey;
import pl.edu.agh.student.model.survey.SurveyResponseRequest;
import pl.edu.agh.student.repository.SurveyResponseRepository;

import java.util.Date;
import java.util.UUID;

@Component
public class SurveyResponseService {

    private SurveyResponseRepository surveyResponseRepository;
    private ProfileCardService profileCardService;
    private DeviceService profileService;

    @Autowired
    public SurveyResponseService(SurveyResponseRepository surveyResponseRepository, ProfileCardService profileCardService, DeviceService profileService) {
        this.surveyResponseRepository = surveyResponseRepository;
        this.profileCardService = profileCardService;
        this.profileService = profileService;
    }

    public void handleResponse(SurveyResponseRequest responseRequest) {

        UUID profileId = UUID.fromString(profileService.getProfileId(responseRequest.getUuid()));
        SurveyResponseKey surveyResponseKey = new SurveyResponseKey(
                profileId,
                new Date(),
                responseRequest.getSurveyId()
        );

        SurveyResponse surveyResponse =
                new SurveyResponse(
                        surveyResponseKey,
                        JacksonUtil.toString(responseRequest),
                        UUID.fromString(responseRequest.getUuid()));

        surveyResponseRepository.save(surveyResponse);

        profileCardService.handleSurveyResponse(profileId, responseRequest.getFieldList());

    }

}
