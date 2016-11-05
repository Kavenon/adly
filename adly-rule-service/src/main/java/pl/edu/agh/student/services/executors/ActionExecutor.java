package pl.edu.agh.student.services.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.Rule;

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
                    specificActionExecutorFactory.getFactory(action.getRuleAction()).execute(action, userEvent));

    }
}
