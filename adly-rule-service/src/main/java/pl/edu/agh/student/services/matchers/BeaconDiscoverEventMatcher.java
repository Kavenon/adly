package pl.edu.agh.student.services.matchers;

import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.event.RuleEvent;

@Component
public class BeaconDiscoverEventMatcher implements ISpecificEventParamsMatcher {

    @Override
    public boolean match(RuleEvent event, UserEvent userEvent) {
        return false;
    }

}
