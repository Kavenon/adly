package pl.edu.agh.student.services.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.notification.NotificationType;
import pl.edu.agh.student.services.NotificationWriter;
import pl.edu.agh.student.services.external.DeviceService;

@Component
public class SendSurveyActionExecutor extends AbstractSendNotificationExecutor implements ISpecificActionExecutor {

    @Autowired
    public SendSurveyActionExecutor(NotificationWriter notificationWriter, DeviceService deviceService) {
        super(notificationWriter, deviceService);
    }

    @Override
    NotificationType getNotificationType() {
        return NotificationType.F;
    }
}
