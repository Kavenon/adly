package pl.edu.agh.student.services.checkers;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.student.model.rule.condition.SurveySentCondition;
import pl.edu.agh.student.model.rule.condition.SurveySentConditionConfig;
import pl.edu.agh.student.services.MockUserEvent;
import pl.edu.agh.student.services.SurveySentService;
import pl.edu.agh.student.services.external.DeviceService;
import pl.edu.agh.student.services.helpers.TimeCalculator;

import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SurveySentConditionCheckerTest {

    private SurveySentConditionChecker checker;
    private SurveySentService surveySentService;

    @Before
    public void test(){

        DeviceService deviceService = mock(DeviceService.class);
        when(deviceService.getProfileId(any())).thenReturn(UUID.randomUUID().toString());
        surveySentService = mock(SurveySentService.class);
        checker = new SurveySentConditionChecker(surveySentService,new TimeCalculator(), deviceService);

    }

    @Test
    public void checkWithoutDateTrue() throws Exception {

        SurveySentCondition condition = getCondition(false);
        condition.getConfig().setCheckDate(false);

        when(surveySentService.surveySent(any(),any())).thenReturn(true);
        boolean check = checker.check(condition, new MockUserEvent());

        assertThat(check).isTrue();

    }

    @Test
    public void checkWithoutDateFalse() throws Exception {

        SurveySentCondition condition = getCondition(false);
        condition.getConfig().setCheckDate(false);

        when(surveySentService.surveySent(any(),any())).thenReturn(false);
        boolean check = checker.check(condition, new MockUserEvent());

        assertThat(check).isFalse();

    }

    @Test
    public void checkWithDateFalse() throws Exception {

        SurveySentCondition condition = getCondition(false);
        condition.getConfig().setCheckDate(true);

        when(surveySentService.surveySent(any(),any(),any())).thenReturn(false);
        boolean check = checker.check(condition, new MockUserEvent());

        assertThat(check).isFalse();

    }

    @Test
    public void checkWithDateTrue() throws Exception {

        SurveySentCondition condition = getCondition(false);
        condition.getConfig().setCheckDate(true);

        when(surveySentService.surveySent(any(),any(),any())).thenReturn(true);
        boolean check = checker.check(condition, new MockUserEvent());

        assertThat(check).isTrue();

    }

    private SurveySentCondition getCondition(boolean negation) {
        SurveySentConditionConfig config = new SurveySentConditionConfig();
        config.setTimeUnit(ChronoUnit.DAYS);
        config.setTimeValue(1);
        config.setNegation(negation);
        config.setSurveyId(1);
        SurveySentCondition condition = new SurveySentCondition();
        condition.setConfig(config);
        return condition;
    }

}