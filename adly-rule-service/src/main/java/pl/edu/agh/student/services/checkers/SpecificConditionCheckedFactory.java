package pl.edu.agh.student.services.checkers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.rule.condition.*;

@Component
public class SpecificConditionCheckedFactory {

    @Autowired
    private ISpecificConditionChecker beaconDiscoverConditionChecker;

    @Autowired
    private ISpecificConditionChecker notificationSentConditionChecker;

    @Autowired
    private ISpecificConditionChecker surveySentConditionChecker;

    @Autowired
    private ISpecificConditionChecker userProfileConditionChecker;

    public ISpecificConditionChecker getFactory(RuleCondition condition) {

        if(condition instanceof BeaconDiscoverCondition){
            return beaconDiscoverConditionChecker;
        }
        else if(condition instanceof NotificationSentCondition){
            return notificationSentConditionChecker;
        }
        else if(condition instanceof SurveySentCondition){
            return surveySentConditionChecker;
        }
        else if(condition instanceof UserProfileCondition){
            return userProfileConditionChecker;
        }

        throw new IllegalArgumentException("Requested condition is not supported. " + condition);

    }
}
