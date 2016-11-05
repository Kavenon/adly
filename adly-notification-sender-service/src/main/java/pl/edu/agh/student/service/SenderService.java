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
    private NotificationSentService notificationSentService;

    @Autowired
    public SenderService(SenderFactory senderFactory, NotificationSentService notificationSentService) {
        this.senderFactory = senderFactory;
        this.notificationSentService = notificationSentService;
    }

    @StreamListener("input")
    public void send(NotificationSendRequest notificationSendRequest){

        Sender sender = senderFactory.getSender(notificationSendRequest.getRecipient().getSystem());
        try {
            sender.send(notificationSendRequest.getNotification(),notificationSendRequest.getRecipient());
            if(notificationSendRequest.getRecipient().getDeviceId() != null){
                notificationSentService.record(notificationSendRequest);
            }
        }
        catch(Exception e){
            LOG.error("Could not send notification " + notificationSendRequest.toString(),e);
        }

    }

}
