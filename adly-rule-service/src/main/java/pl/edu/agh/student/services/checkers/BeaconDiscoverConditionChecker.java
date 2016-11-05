package pl.edu.agh.student.services.checkers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.BeaconDiscoverUserEvent;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.condition.BeaconDiscoverCondition;
import pl.edu.agh.student.model.rule.condition.RuleCondition;
import pl.edu.agh.student.services.external.BeaconService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Component
public class BeaconDiscoverConditionChecker implements ISpecificConditionChecker {

    private BeaconService beaconService;

    @Autowired
    public BeaconDiscoverConditionChecker(BeaconService beaconService) {
        this.beaconService = beaconService;
    }

    @Override
    public boolean check(RuleCondition _condition, UserEvent userEvent) {

        BeaconDiscoverCondition condition = (BeaconDiscoverCondition) _condition;
        String uuid = userEvent.getUuid();

        long sinceMillis = getSinceMillis(condition);

        List<UUID> discoveredBeaconsTraceId = beaconService.getDiscoveredBeaconsTraceId(uuid, condition.getConfig().getBeaconId(), sinceMillis);

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


    private long getSinceMillis(BeaconDiscoverCondition condition) {
        return LocalDateTime
                    .now()
                    .minus(condition.getConfig().getTimeValue(), condition.getConfig().getTimeUnit())
                    .toEpochSecond(ZoneOffset.UTC) * 1000;
    }
}
