package pl.edu.agh.student.services.matchers;

import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.BeaconDiscoverUserEvent;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.event.BeaconDiscoverEvent;
import pl.edu.agh.student.model.rule.event.RuleEvent;

import java.util.Objects;

@Component
public class BeaconDiscoverEventMatcher implements ISpecificEventParamsMatcher {

    @Override
    public boolean match(RuleEvent _event, UserEvent _userEvent) {

        BeaconDiscoverEvent ruleEvent = (BeaconDiscoverEvent) _event;
        BeaconDiscoverUserEvent userEvent = (BeaconDiscoverUserEvent) _userEvent;

        return Objects.equals(ruleEvent.getConfig().getBeaconId(), userEvent.getBeacon().getId());

    }

}
