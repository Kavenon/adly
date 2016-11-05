package pl.edu.agh.student.services.checkers;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.student.event.BeaconDiscoverUserEvent;
import pl.edu.agh.student.model.rule.condition.BeaconDiscoverCondition;
import pl.edu.agh.student.model.rule.condition.BeaconDiscoverConditionConfig;
import pl.edu.agh.student.services.external.BeaconService;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BeaconDiscoverConditionCheckerTest {

    private BeaconService beaconService;
    private BeaconDiscoverConditionChecker beaconDiscoverConditionChecker;
    @Before
    public void setUp(){
        beaconService = mock(BeaconService.class);
        beaconDiscoverConditionChecker = new BeaconDiscoverConditionChecker(beaconService);
    }

    @Test
    public void check() throws Exception {

        when(beaconService.getDiscoveredBeaconsTraceId(any(), any(),any())).thenReturn(new ArrayList<>());

        boolean check = beaconDiscoverConditionChecker.check(getCondition(), new BeaconDiscoverUserEvent());
        assertThat(check).isTrue();

    }

    @Test
    public void checkEventBeingProcessed() throws Exception {

        UUID traceId = UUID.randomUUID();
        when(beaconService.getDiscoveredBeaconsTraceId(any(), any(),any())).thenReturn(Collections.singletonList(traceId));

        BeaconDiscoverUserEvent userEvent = new BeaconDiscoverUserEvent();
        userEvent.setTraceId(traceId.toString());

        boolean check = beaconDiscoverConditionChecker.check(getCondition(), userEvent);
        assertThat(check).isTrue();

    }

    @Test
    public void checkFalse() throws Exception {

        when(beaconService.getDiscoveredBeaconsTraceId(any(), any(),any())).thenReturn(Collections.singletonList(UUID.randomUUID()));

        BeaconDiscoverUserEvent userEvent = new BeaconDiscoverUserEvent();
        userEvent.setTraceId(UUID.randomUUID().toString());

        boolean check = beaconDiscoverConditionChecker.check(getCondition(), userEvent);
        assertThat(check).isFalse();

    }

    @Test
    public void checkFalse2() throws Exception {

        UUID traceId = UUID.randomUUID();
        List<UUID> uuids = new ArrayList<>();
        uuids.add(traceId);
        uuids.add(UUID.randomUUID());
        when(beaconService.getDiscoveredBeaconsTraceId(any(), any(),any())).thenReturn(uuids);

        BeaconDiscoverUserEvent userEvent = new BeaconDiscoverUserEvent();
        userEvent.setTraceId(traceId.toString());

        boolean check = beaconDiscoverConditionChecker.check(getCondition(), userEvent);
        assertThat(check).isFalse();

    }

    private BeaconDiscoverCondition getCondition() {
        BeaconDiscoverConditionConfig config = new BeaconDiscoverConditionConfig();
        config.setBeaconId(1);
        config.setTimeUnit(ChronoUnit.DAYS);
        config.setTimeValue(1);
        BeaconDiscoverCondition condition = new BeaconDiscoverCondition();
        condition.setConfig(config);
        return condition;
    }


}