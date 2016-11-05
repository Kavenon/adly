package pl.edu.agh.student.services.executors;

import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.RuleActionEntity;

public interface ISpecificActionExecutor {
    void execute(RuleActionEntity action, UserEvent userEvent);
}
