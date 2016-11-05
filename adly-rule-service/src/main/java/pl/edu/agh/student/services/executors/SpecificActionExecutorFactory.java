package pl.edu.agh.student.services.executors;

import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.rule.action.RuleAction;

@Component
public class SpecificActionExecutorFactory {
    public ISpecificActionExecutor getFactory(RuleAction action) {
        return null;
    }
}
