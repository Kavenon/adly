package pl.edu.agh.student.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.NotificationSendRequest;
import pl.edu.agh.student.model.NotificationSent;
import pl.edu.agh.student.model.NotificationSentKey;
import pl.edu.agh.student.model.notification.Notification;
import pl.edu.agh.student.repository.NotificationSentRepository;
import pl.edu.agh.student.sender.FcmSender;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class NotificationSentService {

    private static final Log LOG = LogFactory.getLog(FcmSender.class);

    private static final ObjectMapper om = new ObjectMapper();

    private NotificationSentRepository notificationSentRepository;

    @Autowired
    public NotificationSentService(NotificationSentRepository notificationSentRepository) {
        this.notificationSentRepository = notificationSentRepository;
    }

    public void record(NotificationSendRequest notificationSendRequest){

        NotificationSentKey notificationSentKey = new NotificationSentKey(
                notificationSendRequest.getRecipient().getDeviceId(),
                new Date()
        );
        NotificationSent notificationSent = new NotificationSent(notificationSentKey, mapToJson(notificationSendRequest.getNotification()));

        notificationSentRepository.save(notificationSent);

    }

    private String mapToJson(Notification notification) {

        try {
            return om.writeValueAsString(notification);
        } catch (JsonProcessingException e) {
            LOG.error("Could not save as json", e);
            return null;
        }

    }

    @PreAuthorize("#oauth2.hasScope('server')")
    public boolean hasSentToDevice(UUID deviceId, Long sinceMillis) {

        List<NotificationSent> notificationSent =
                notificationSentRepository.hasSentToDevice(deviceId, new Date(sinceMillis));

        return notificationSent.size() != 0;

    }
}
