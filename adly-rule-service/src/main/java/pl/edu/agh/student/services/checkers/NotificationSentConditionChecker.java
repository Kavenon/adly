package pl.edu.agh.student.services.checkers;

import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.condition.RuleCondition;

@Component
public class NotificationSentConditionChecker implements ISpecificConditionChecker {

    @Override
    public boolean check(RuleCondition condition, UserEvent userEvent) {
        return false;

//        if(condition.getConfig().isNegation()){
//            return !hasDiscoverBeacon;
//        }
//        return hasDiscoverBeacon;

    }
}
