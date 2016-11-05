package pl.edu.agh.student.services.checkers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.BeaconDiscoverUserEvent;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.condition.BeaconDiscoverCondition;
import pl.edu.agh.student.model.rule.condition.RuleCondition;
import pl.edu.agh.student.services.external.BeaconService;
import pl.edu.agh.student.services.helpers.TimeCalculator;

import java.util.List;
import java.util.UUID;

@Component
public class BeaconDiscoverConditionChecker implements ISpecificConditionChecker {

    private BeaconService beaconService;
    private TimeCalculator timeCalculator;

    @Autowired
    public BeaconDiscoverConditionChecker(BeaconService beaconService, TimeCalculator timeCalculator) {
        this.beaconService = beaconService;
        this.timeCalculator = timeCalculator;
    }

    @Override
    public boolean check(RuleCondition _condition, UserEvent userEvent) {

        BeaconDiscoverCondition condition = (BeaconDiscoverCondition) _condition;
        String uuid = userEvent.getUuid();

        long sinceMillis = timeCalculator.millisFromNow(condition.getConfig().getTimeValue(),condition.getConfig().getTimeUnit());

        List<UUID> discoveredBeaconsTraceId = beaconService.getDiscoveredBeaconsTraceId(uuid, condition.getConfig().getBeaconId(), sinceMillis);

        return conditionResult(userEvent, condition, discoveredBeaconsTraceId);

    }

    private boolean conditionResult(UserEvent userEvent, BeaconDiscoverCondition condition, List<UUID> discoveredBeaconsTraceId) {
        boolean hasDiscoverBeacon = hasDiscoverBeacon(userEvent, discoveredBeaconsTraceId);

        if(condition.getConfig().isNegation()){
            return !hasDiscoverBeacon;
        }
        return hasDiscoverBeacon;
    }

    private boolean hasDiscoverBeacon(UserEvent userEvent, List<UUID> discoveredBeaconsTraceId) {
        if(discoveredBeaconsTraceId.size() == 0){
            return true;
        }
        else {
            if(userEvent instanceof BeaconDiscoverUserEvent && discoveredBeaconsTraceId.size() == 1){
                BeaconDiscoverUserEvent beaconDiscoverUserEvent = (BeaconDiscoverUserEvent) userEvent;
                UUID traceId = UUID.fromString(beaconDiscoverUserEvent.getTraceId());
                return discoveredBeaconsTraceId.contains(traceId);
            }
        }
        return false;
    }

}
