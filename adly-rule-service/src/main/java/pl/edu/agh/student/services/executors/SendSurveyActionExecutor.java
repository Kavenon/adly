package pl.edu.agh.student.services.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.RuleActionEntity;
import pl.edu.agh.student.model.rule.SurveySent;
import pl.edu.agh.student.model.rule.SurveySentKey;
import pl.edu.agh.student.model.rule.action.SendSurveyAction;
import pl.edu.agh.student.repository.SurveySentRepository;
import pl.edu.agh.student.services.NotificationWriter;
import pl.edu.agh.student.services.external.DeviceService;

import java.util.Date;
import java.util.UUID;

@Component
public class SendSurveyActionExecutor extends AbstractSendNotificationExecutor implements ISpecificActionExecutor {

    private SurveySentRepository surveySentRepository;

    @Autowired
    public SendSurveyActionExecutor(NotificationWriter notificationWriter, DeviceService deviceService, SurveySentRepository surveySentRepository) {
        super(notificationWriter, deviceService);
        this.surveySentRepository = surveySentRepository;
    }

    @Override
    public void execute(RuleActionEntity action, UserEvent userEvent) {
        super.execute(action, userEvent);

        SurveySent entity = getSurveySent(action, userEvent);
        surveySentRepository.save(entity);

    }

    private SurveySent getSurveySent(RuleActionEntity action, UserEvent userEvent) {
        UUID profileId = UUID.fromString(deviceService.getProfileId(userEvent.getUuid()));

        SurveySentKey surveySentKey =
                new SurveySentKey(
                        profileId,
                        ((SendSurveyAction)action.getRuleAction()).getConfig().getSurveyId(),
                        new Date()
                );

        return new SurveySent(surveySentKey);
    }
}
