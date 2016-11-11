package pl.edu.agh.student;

import org.junit.Ignore;
import org.junit.Test;
import pl.edu.agh.student.model.System;
import pl.edu.agh.student.model.notification.Notification;
import pl.edu.agh.student.model.receipent.Recipient;
import pl.edu.agh.student.sender.FcmSender;

import java.util.Optional;

public class AdlyNotificationServiceTest {

    @Test
    @Ignore
    public void test() throws Exception {

        FcmConnection connection =
                new FcmConnection("gcm.googleapis.com","5236","42996188329", "AIzaSyCgHMhZMDZtJjuajw4kLu36_RgSED5GlKg");

        connection.connect();
        connection.login();

        Notification notification = new Notification();
        notification.setId(29);
        notification.setText("Text");
        notification.setTitle("Title");

        Recipient recipient = new Recipient();
        recipient.setSystem(System.ANDROID);
        recipient.setToken("dNTTzkq9z2U:APA91bHl-yP_vrkSuqQKWeNHDeUAbz-06DHTVw-_5WTWMYMwXjHOSagNU_wvB7i1vSP2hYdfKBAy8CQ0Fi7b0ozmt-Ls-xsyDj63Pw9SsE49aSBS-yl_CAENZwiKOeaxSFEsAWER_212");
        new FcmSender(()-> Optional.of(connection)).send(notification, recipient);

    }
}