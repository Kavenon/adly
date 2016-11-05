package pl.edu.agh.student.services.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.Rule;
import pl.edu.agh.student.model.rule.action.RuleAction;

@Component
public class ActionExecutor {

    private SpecificActionExecutorFactory specificActionExecutorFactory;

    @Autowired
    public ActionExecutor(SpecificActionExecutorFactory specificActionExecutorFactory) {
        this.specificActionExecutorFactory = specificActionExecutorFactory;
    }

    public void execute(Rule rule, UserEvent userEvent) {

        rule.getActions()
            .stream()
            .forEach(action ->
                    specificActionExecutorFactory.getFactory(action.getRuleAction()).execute((RuleAction) action, userEvent));

    }
}
