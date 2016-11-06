package pl.edu.agh.student.services.payload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.rule.action.RuleAction;
import pl.edu.agh.student.model.rule.action.SendSurveyAction;
import pl.edu.agh.student.services.IPayloadFetcher;
import pl.edu.agh.student.services.external.ProfileService;

@Component
public class SurveyPayloadFetcher implements IPayloadFetcher {

    private ProfileService profileService;

    @Autowired
    public SurveyPayloadFetcher(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public Object payload(RuleAction _ruleAction){
        SendSurveyAction ruleAction = (SendSurveyAction) _ruleAction;
        return profileService.getClientSurvey(ruleAction.getConfig().getSurveyId());
    }
}
