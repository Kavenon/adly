package pl.edu.agh.student.model;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("notification_sent")
public class NotificationSent {

    @PrimaryKey
    private NotificationSentKey notificationSentKey;

    @Column(value = "notification")
    private String notificationJson;

    public NotificationSent(NotificationSentKey notificationSentKey, String notificationJson) {
        this.notificationSentKey = notificationSentKey;
        this.notificationJson = notificationJson;
    }

    public NotificationSentKey getNotificationSentKey() {
        return notificationSentKey;
    }

    public void setNotificationSentKey(NotificationSentKey notificationSentKey) {
        this.notificationSentKey = notificationSentKey;
    }

    public String getNotificationJson() {
        return notificationJson;
    }

    public void setNotificationJson(String notificationJson) {
        this.notificationJson = notificationJson;
    }

    @Override
    public String toString() {
        return "NotificationSent{" +
                "notificationSentKey=" + notificationSentKey +
                ", notificationJson='" + notificationJson + '\'' +
                '}';
    }

}
