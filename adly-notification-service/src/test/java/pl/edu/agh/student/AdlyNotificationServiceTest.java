package pl.edu.agh.student;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.junit.Test;

import java.io.IOException;

public class AdlyNotificationServiceTest {

    @Test
    public void test() throws XMPPException, IOException, SmackException {

        FcmConnection connection = new FcmConnection("gcm.googleapis.com","5236","42996188329", "AIzaSyCgHMhZMDZtJjuajw4kLu36_RgSED5GlKg");

        connection.connect();
        connection.login();


        connection.send(GcmTools.createJsonMessage("dNTTzkq9z2U:APA91bHl-yP_vrkSuqQKWeNHDeUAbz-06DHTVw-_5WTWMYMwXjHOSagNU_wvB7i1vSP2hYdfKBAy8CQ0Fi7b0ozmt-Ls-xsyDj63Pw9SsE49aSBS-yl_CAENZwiKOeaxSFEsAWER_212", GcmTools.nextMessageId(), "{\"id\":1,\"title\":\"title\",\"text\":\"text\",\"type\":\"F\"}"));



//        fOKrpAWgWGA:APA91bGiGNSoD_CJRPQf8WXFfaXmm_c3-BPh6qL1OnnUSRI4t3dr18KPYHIR4CPz31kzeWHC5YAhLDJlN5Rl3bSXP8g51Q_LGR7Ku1FT6v4P47gJJVyycnsnAq5-M8rY3MhKVy_r7dho

    }
}