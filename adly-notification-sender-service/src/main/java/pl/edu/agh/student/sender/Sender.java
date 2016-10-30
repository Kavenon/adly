package pl.edu.agh.student.sender;

import pl.edu.agh.student.model.notification.Notification;
import pl.edu.agh.student.model.receipent.Recipient;

public interface Sender {

    void send(Notification notification, Recipient recipient) throws Exception;

}
