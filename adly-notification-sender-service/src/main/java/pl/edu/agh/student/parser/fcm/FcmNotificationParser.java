package pl.edu.agh.student.parser.fcm;

import pl.edu.agh.student.JacksonUtil;
import pl.edu.agh.student.model.notification.Notification;
import pl.edu.agh.student.model.receipent.Recipient;

public class FcmNotificationParser {

    public static String parse(Notification notification, Recipient recipient) {

        FcmNotification fcmNotification =
                new FcmNotification(notification, recipient.getToken());

        return JacksonUtil.toString(fcmNotification);

    }
}
