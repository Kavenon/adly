package pl.edu.agh.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.ProfileCard;
import pl.edu.agh.student.model.ProfileCardKey;
import pl.edu.agh.student.model.survey.SurveyResponseField;
import pl.edu.agh.student.repository.ProfileCardRepository;

import java.util.List;
import java.util.UUID;

@Component
public class ProfileCardService {

    private ProfileCardRepository profileCardRepository;

    @Autowired
    public ProfileCardService(ProfileCardRepository profileCardRepository) {
        this.profileCardRepository = profileCardRepository;
    }

    public void handleSurveyResponse(UUID profileId, List<SurveyResponseField> fieldList) {

        fieldList.forEach(x->{
            ProfileCard profileCard = new ProfileCard(
                    new ProfileCardKey(profileId, x.getId()),
                    x.getValue()
            );
            profileCardRepository.save(profileCard);
        });

    }

    public String getPropertyValueForUuid(UUID profileUuid, Integer propertyId) {

        ProfileCard byProfileIdAndPropertyId = profileCardRepository.findByProfileIdAndPropertyId(profileUuid, propertyId);

        if(byProfileIdAndPropertyId == null){
            return null;
        }
        return byProfileIdAndPropertyId.getValue();

    }
}
