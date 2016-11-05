package pl.edu.agh.student.services.checkers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.condition.NotificationSentCondition;
import pl.edu.agh.student.model.rule.condition.RuleCondition;
import pl.edu.agh.student.services.external.NotificationSenderService;
import pl.edu.agh.student.services.helpers.TimeCalculator;

import java.util.UUID;

@Component
public class NotificationSentConditionChecker implements ISpecificConditionChecker {

    private NotificationSenderService notificationSenderService;
    private TimeCalculator timeCalculator;

    @Autowired
    public NotificationSentConditionChecker(NotificationSenderService notificationSenderService, TimeCalculator timeCalculator) {
        this.notificationSenderService = notificationSenderService;
        this.timeCalculator = timeCalculator;
    }

    @Override
    public boolean check(RuleCondition _condition, UserEvent userEvent) {

        NotificationSentCondition condition = (NotificationSentCondition) _condition;

        Long sinceMillis =
                timeCalculator.millisFromNow(condition.getConfig().getTimeValue(), condition.getConfig().getTimeUnit());

        boolean hasSentNotification =
                notificationSenderService.hasSentNotification(UUID.fromString(userEvent.getUuid()), sinceMillis);

        if(condition.getConfig().isNegation()){
            return !hasSentNotification;
        }
        return hasSentNotification;

    }
}
