package pl.edu.agh.student.sender;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.FcmConnection;
import pl.edu.agh.student.IFcmConnectionProvider;
import pl.edu.agh.student.model.notification.Notification;
import pl.edu.agh.student.model.receipent.Recipient;
import pl.edu.agh.student.parser.fcm.FcmNotificationParser;

import java.util.Optional;

@Component
public class FcmSender implements Sender {

    private static final Log LOG = LogFactory.getLog(FcmSender.class);

    private IFcmConnectionProvider fcmConnectionProvider;

    @Autowired
    public FcmSender(IFcmConnectionProvider fcmConnectionProvider) {
        this.fcmConnectionProvider = fcmConnectionProvider;
    }

    @Override
    public void send(Notification notification, Recipient recipient) throws Exception {

        Optional<FcmConnection> fcmConnection = fcmConnectionProvider.getFcmConnection();

        if(fcmConnection.isPresent()){
            fcmConnection.get().send(FcmNotificationParser.parse(notification,recipient));
        }
        else {
            LOG.error("Fcm connection does not exist");
        }

    }

}
