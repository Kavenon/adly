package pl.edu.agh.student.services;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import pl.edu.agh.student.model.NotificationSendRequest;

@MessagingGateway
public interface NotificationWriter {

	@Gateway(requestChannel = "output")
    void send(NotificationSendRequest event);

}
