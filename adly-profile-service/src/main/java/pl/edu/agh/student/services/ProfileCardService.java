package pl.edu.agh.student.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.ProfileCard;
import pl.edu.agh.student.model.ProfileCardKey;
import pl.edu.agh.student.model.survey.SurveyResponseField;
import pl.edu.agh.student.repository.ProfileCardRepository;
import pl.edu.agh.student.repository.UserSurveyRepository;

import java.util.List;
import java.util.UUID;

@Component
public class ProfileCardService {

    private static final Log LOG = LogFactory.getLog(ProfileCardService.class);

    private ProfileCardRepository profileCardRepository;
    private UserSurveyRepository surveyRepository;

    @Autowired
    public ProfileCardService(ProfileCardRepository profileCardRepository, UserSurveyRepository surveyRepository) {
        this.profileCardRepository = profileCardRepository;
        this.surveyRepository = surveyRepository;
    }

    public void handleSurveyResponse(UUID profileId, List<SurveyResponseField> fieldList) {

        fieldList.forEach(x->{
            Integer propertyIdByFieldId = surveyRepository.findPropertyIdByFieldId(x.getId());
            if(propertyIdByFieldId != null){
                ProfileCard profileCard = new ProfileCard(
                        new ProfileCardKey(profileId, propertyIdByFieldId),
                        x.getValue()
                );
                profileCardRepository.save(profileCard);
            }
            else {
                LOG.error("Could not find profile property by field id: " + x.getId());
            }

        });

    }

    public String getPropertyValueForUuid(UUID profileUuid, Integer propertyId) {

        ProfileCard one = profileCardRepository.findOne(new ProfileCardKey(profileUuid, propertyId));

        if(one == null){
            return null;
        }
        return one.getValue();

    }
}
