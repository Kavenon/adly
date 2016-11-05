package pl.edu.agh.student.services.matchers;

import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.event.RuleEvent;

public interface ISpecificEventParamsMatcher {

    boolean match(RuleEvent event, UserEvent userEvent);
}
