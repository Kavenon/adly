package pl.edu.agh.student.services.checkers;

import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.condition.RuleCondition;

public interface ISpecificConditionChecker {

    boolean check(RuleCondition condition, UserEvent userEvent);

}
