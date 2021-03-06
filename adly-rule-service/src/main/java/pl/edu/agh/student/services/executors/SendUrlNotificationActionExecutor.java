package pl.edu.agh.student.services.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.services.NotificationWriter;
import pl.edu.agh.student.services.external.DeviceService;

@Component
public class SendUrlNotificationActionExecutor extends AbstractSendNotificationExecutor implements ISpecificActionExecutor {

    @Autowired
    public SendUrlNotificationActionExecutor(NotificationWriter notificationWriter, DeviceService deviceService) {
        super(notificationWriter, deviceService);
    }

}
