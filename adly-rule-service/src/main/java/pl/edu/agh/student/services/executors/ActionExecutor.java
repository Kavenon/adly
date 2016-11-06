package pl.edu.agh.student.services.executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.Rule;
import pl.edu.agh.student.services.matchers.EventParamsMatcher;

@Component
public class ActionExecutor {

    private static final Log LOG = LogFactory.getLog(EventParamsMatcher.class);

    private SpecificActionExecutorFactory specificActionExecutorFactory;

    @Autowired
    public ActionExecutor(SpecificActionExecutorFactory specificActionExecutorFactory) {
        this.specificActionExecutorFactory = specificActionExecutorFactory;
    }

    public void execute(Rule rule, UserEvent userEvent) {

        LOG.info("Executing actions for rule: " + rule);

        rule.getActions()
            .stream()
            .forEach(action -> {
                LOG.info("Executing action: " + action);
                specificActionExecutorFactory.getFactory(action.getRuleAction()).execute(action, userEvent);
            });

    }
}
