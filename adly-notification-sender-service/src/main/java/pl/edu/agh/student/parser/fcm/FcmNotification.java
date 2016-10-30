package pl.edu.agh.student.parser.fcm;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.edu.agh.student.model.notification.Notification;

import java.util.UUID;

public class FcmNotification {

    private String to;

    @JsonProperty("message_id")
    private String messageId = "msg_" + UUID.randomUUID();

    private Notification data;

    public FcmNotification(Notification data, String to) {
        this.data = data;
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessageId() {
        return messageId;
    }

    public Notification getData() {
        return data;
    }

    public void setData(Notification data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FcmNotification{" +
                "to='" + to + '\'' +
                ", messageId='" + messageId + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
