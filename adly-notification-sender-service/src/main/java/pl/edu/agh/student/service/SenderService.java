package pl.edu.agh.student.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
import pl.edu.agh.student.sender.FcmSender;
import pl.edu.agh.student.sender.Sender;
import pl.edu.agh.student.sender.SenderFactory;
import pl.edu.agh.student.model.NotificationSendRequest;

@MessageEndpoint
public class SenderService {

    private static final Log LOG = LogFactory.getLog(FcmSender.class);

    private SenderFactory senderFactory;

    @Autowired
    public SenderService(SenderFactory senderFactory) {
        this.senderFactory = senderFactory;
    }

    @StreamListener("input")
    public void send(NotificationSendRequest notificationSendRequest){

        Sender sender = senderFactory.getSender(notificationSendRequest.getRecipient().getSystem());
        try {
            sender.send(notificationSendRequest.getNotification(),notificationSendRequest.getRecipient());
        }
        catch(Exception e){
            LOG.error("Could not send notification " + notificationSendRequest.toString(),e);
        }

    }

}
