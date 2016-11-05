package pl.edu.agh.student.services.checkers;

import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.condition.RuleCondition;

@Component
public class SurveySentConditionChecker implements ISpecificConditionChecker {

    @Override
    public boolean check(RuleCondition condition, UserEvent userEvent) {
        return false;
    }
}
