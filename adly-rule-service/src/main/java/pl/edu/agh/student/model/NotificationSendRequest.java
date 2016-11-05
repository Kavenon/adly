package pl.edu.agh.student.model;

import pl.edu.agh.student.model.notification.Notification;
import pl.edu.agh.student.model.receipent.Recipient;

public class NotificationSendRequest {

    private Notification notification;
    private Recipient recipient;

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "NotificationSendRequest{" +
                "notification=" + notification +
                ", recipient=" + recipient +
                '}';
    }
}
