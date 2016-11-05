package pl.edu.agh.student.services.matchers;

import org.junit.Test;
import pl.edu.agh.student.event.Beacon;
import pl.edu.agh.student.event.BeaconDiscoverUserEvent;
import pl.edu.agh.student.model.rule.event.BeaconDiscoverEvent;
import pl.edu.agh.student.model.rule.event.BeaconDiscoverEventConfig;

import static org.assertj.core.api.Assertions.assertThat;

public class BeaconDiscoverEventMatcherTest {

    private BeaconDiscoverEventMatcher beaconDiscoverEventMatcher = new BeaconDiscoverEventMatcher();

    @Test
    public void matchTrue() throws Exception {

        BeaconDiscoverEvent event = getEvent(1);
        BeaconDiscoverUserEvent beaconDiscoverUserEvent = getUserEvent(1);

        boolean match = beaconDiscoverEventMatcher.match(event, beaconDiscoverUserEvent);
        assertThat(match).isTrue();

    }

    @Test
    public void matchFalse() throws Exception {

        BeaconDiscoverEvent event = getEvent(1);
        BeaconDiscoverUserEvent beaconDiscoverUserEvent = getUserEvent(2);

        boolean match = beaconDiscoverEventMatcher.match(event, beaconDiscoverUserEvent);
        assertThat(match).isFalse();

    }

    private BeaconDiscoverUserEvent getUserEvent(int beaconId) {
        BeaconDiscoverUserEvent beaconDiscoverUserEvent = new BeaconDiscoverUserEvent();
        Beacon beacon = new Beacon();
        beacon.setId(beaconId);
        beaconDiscoverUserEvent.setBeacon(beacon);
        return beaconDiscoverUserEvent;
    }

    private BeaconDiscoverEvent getEvent(int beaconId) {
        BeaconDiscoverEventConfig config = new BeaconDiscoverEventConfig();
        config.setBeaconId(beaconId);
        BeaconDiscoverEvent beaconDiscoverEvent = new BeaconDiscoverEvent();
        beaconDiscoverEvent.setConfig(config);
        return beaconDiscoverEvent;
    }

}