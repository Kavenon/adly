package pl.edu.agh.student.services.checkers;

import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.rule.condition.RuleCondition;

@Component
public class SpecificConditionCheckedFactory {
    public ISpecificConditionChecker getFactory(RuleCondition condition) {
        return null;
    }
}
