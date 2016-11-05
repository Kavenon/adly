package pl.edu.agh.student.services.executors;

import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.action.RuleAction;

public interface ISpecificActionExecutor {
    void execute(RuleAction action, UserEvent userEvent);
}
