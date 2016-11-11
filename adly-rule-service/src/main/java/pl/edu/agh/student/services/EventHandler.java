package pl.edu.agh.student.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.Rule;
import pl.edu.agh.student.repository.RuleRepository;
import pl.edu.agh.student.services.checkers.ConditionChecker;
import pl.edu.agh.student.services.executors.ActionExecutor;
import pl.edu.agh.student.services.matchers.EventParamsMatcher;

import java.util.List;

@Component
public class EventHandler {

    private static final Log LOG = LogFactory.getLog(EventHandler.class);

    private RuleRepository ruleRepository;
    private EventParamsMatcher eventParamsMatcher;
    private ConditionChecker conditionChecker;
    private ActionExecutor actionExecutor;

    @Autowired
    public EventHandler(RuleRepository ruleRepository, EventParamsMatcher eventParamsMatcher, ConditionChecker conditionChecker, ActionExecutor actionExecutor) {
        this.ruleRepository = ruleRepository;
        this.eventParamsMatcher = eventParamsMatcher;
        this.conditionChecker = conditionChecker;
        this.actionExecutor = actionExecutor;
    }

    public void handleEvent(UserEvent userEvent){

        long startMillis = System.currentTimeMillis();

        LOG.info("Received event: " + userEvent);

        List<Rule> activeRulesForEvent = getActiveRulesForEvent(userEvent);

        LOG.info("Found rules: " + activeRulesForEvent.size());

        activeRulesForEvent
                .stream()
                .filter(rule -> eventParamsMatcher.match(rule, userEvent))
                .filter(rule -> conditionChecker.check(rule, userEvent))
                .forEach(rule -> actionExecutor.execute(rule, userEvent));

        LOG.info("Processing rules finished in: " + (System.currentTimeMillis() - startMillis));

    }

    private List<Rule> getActiveRulesForEvent(UserEvent userEvent) {
        List<Rule> activeRulesForEvent;
        if(userEvent.getUserId() == null){
            //todo: add additional query. Now it will take all active users, will be better to take only this with selected predefined beacon
            activeRulesForEvent =
                    ruleRepository.findByActiveAndEventType(true, buildPqJsonParam(userEvent));
        }
        else {
            activeRulesForEvent =
                    ruleRepository.findByUserIdAndActiveAndEventType(userEvent.getUserId(), true, buildPqJsonParam(userEvent));
        }
        return activeRulesForEvent;
    }


    // todo: create custom interface impementation and simplify query
    private String buildPqJsonParam(UserEvent userEvent) {
        return "[{\"type\":\""+userEvent.getModelType()+"\"}]";
    }

}
