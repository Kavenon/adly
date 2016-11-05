package pl.edu.agh.student.services.checkers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.condition.RuleCondition;
import pl.edu.agh.student.model.rule.condition.SurveySentCondition;
import pl.edu.agh.student.services.SurveySentService;
import pl.edu.agh.student.services.external.ProfileService;
import pl.edu.agh.student.services.helpers.TimeCalculator;

import java.util.UUID;

@Component
public class SurveySentConditionChecker implements ISpecificConditionChecker {

    private SurveySentService surveySentService;
    private TimeCalculator timeCalculator;
    private ProfileService profileService;

    @Autowired
    public SurveySentConditionChecker(SurveySentService surveySentService, TimeCalculator timeCalculator, ProfileService profileService) {
        this.surveySentService = surveySentService;
        this.timeCalculator = timeCalculator;
        this.profileService = profileService;
    }

    @Override
    public boolean check(RuleCondition _condition, UserEvent userEvent) {

        SurveySentCondition condition = (SurveySentCondition) _condition;

        UUID profileUuid = UUID.fromString(profileService.getProfileId(userEvent.getUuid()));

        return conditionResult(condition, profileUuid);

    }

    private boolean conditionResult(SurveySentCondition condition, UUID profileUuid) {
        boolean wasSurveySent = wasSurveySent(condition, profileUuid);

        if(condition.getConfig().isNegation()){
            return !wasSurveySent;
        }
        return wasSurveySent;
    }

    private boolean wasSurveySent(SurveySentCondition condition, UUID profileUuid) {
        if(condition.getConfig().isCheckDate()){
            return surveySentService.surveySent(profileUuid,condition.getConfig().getSurveyId(), timeCalculator.dateFromNow(condition.getConfig().getTimeValue(),condition.getConfig().getTimeUnit()));
        }
        else {
            return surveySentService.surveySent(profileUuid,condition.getConfig().getSurveyId());
        }
    }
}
