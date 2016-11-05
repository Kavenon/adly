package pl.edu.agh.student.services.matchers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.Rule;
import pl.edu.agh.student.model.rule.event.RuleEvent;

import java.util.Arrays;
import java.util.List;

@Component
public class EventParamsMatcher {

    private static final Log LOG = LogFactory.getLog(EventParamsMatcher.class);
    private SpecificEventParamsMatcherFactory specificEventParamsMatcherFactory;

    @Autowired
    public EventParamsMatcher(SpecificEventParamsMatcherFactory specificEventParamsMatcherFactory) {
        this.specificEventParamsMatcherFactory = specificEventParamsMatcherFactory;
    }

    public boolean match(Rule rule, UserEvent userEvent) {

        List<RuleEvent> ruleEvents = Arrays.asList(rule.getEvents());

        return ruleEvents
                .stream()
                .filter(event -> event.getClass().isInstance(userEvent.getRuleEvent()))
                .anyMatch(event -> checkIfEventMatches(userEvent, event));
    }

    private boolean checkIfEventMatches(UserEvent userEvent, RuleEvent event) {
        boolean match = specificEventParamsMatcherFactory.getFactory(event).match(event,userEvent);
        if(match){
            LOG.info("Event matched: " + event + " for user event: " + userEvent);
        }
        else {
            LOG.info("Event not matched: " + event + " for user event: " + userEvent);
        }
        return match;
    }

}
