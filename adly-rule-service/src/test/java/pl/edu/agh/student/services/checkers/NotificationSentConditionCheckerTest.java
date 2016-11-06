package pl.edu.agh.student.services.checkers;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.condition.NotificationSentCondition;
import pl.edu.agh.student.model.rule.condition.NotificationSentConditionConfig;
import pl.edu.agh.student.services.MockUserEvent;
import pl.edu.agh.student.services.external.NotificationSenderService;
import pl.edu.agh.student.services.helpers.TimeCalculator;

import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NotificationSentConditionCheckerTest {

    private NotificationSentConditionChecker checker;
    private NotificationSenderService senderService;

    @Before
    public void setUp(){

        senderService = mock(NotificationSenderService.class);
        checker = new NotificationSentConditionChecker(senderService, new TimeCalculator());

    }

    @Test
    public void check() throws Exception {

        when(senderService.hasSentNotification(any(),any())).thenReturn(true);
        boolean result = checker.check(getCondition(false), mockUserEvent());
        assertThat(result).isTrue();

    }

    @Test
    public void checkFalse() throws Exception {

        when(senderService.hasSentNotification(any(),any())).thenReturn(false);
        boolean result = checker.check(getCondition(false), mockUserEvent());
        assertThat(result).isFalse();

    }

    private UserEvent mockUserEvent() {
        return new MockUserEvent();
    }

    private NotificationSentCondition getCondition(boolean negation) {
        NotificationSentConditionConfig config = new NotificationSentConditionConfig();
        config.setTimeUnit(ChronoUnit.DAYS);
        config.setTimeValue(1);
        config.setNegation(negation);
        NotificationSentCondition condition = new NotificationSentCondition();
        condition.setConfig(config);
        return condition;
    }
}