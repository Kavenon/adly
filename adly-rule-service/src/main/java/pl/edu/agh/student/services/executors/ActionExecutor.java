package pl.edu.agh.student.services.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.Rule;
import pl.edu.agh.student.model.rule.action.RuleAction;

import java.util.Arrays;
import java.util.List;

@Component
public class ActionExecutor {

    private SpecificActionExecutorFactory specificActionExecutorFactory;

    @Autowired
    public ActionExecutor(SpecificActionExecutorFactory specificActionExecutorFactory) {
        this.specificActionExecutorFactory = specificActionExecutorFactory;
    }

    public void execute(Rule rule, UserEvent userEvent) {

        List<RuleAction> ruleActions = Arrays.asList(rule.getActions());

        ruleActions
            .stream()
            .filter(event -> event.getClass().isInstance(userEvent.getRuleEvent()))
            .forEach(action -> specificActionExecutorFactory.getFactory(action).execute(action, userEvent));

    }
}
