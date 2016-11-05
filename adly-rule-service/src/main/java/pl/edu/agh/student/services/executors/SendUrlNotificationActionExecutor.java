package pl.edu.agh.student.services.executors;

import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.action.RuleAction;

@Component
public class SendUrlNotificationActionExecutor implements ISpecificActionExecutor {

    @Override
    public void execute(RuleAction action, UserEvent userEvent) {

    }

}
