package pl.edu.agh.student.services.executors;

import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.NotificationSendRequest;
import pl.edu.agh.student.model.System;
import pl.edu.agh.student.model.notification.Notification;
import pl.edu.agh.student.model.notification.NotificationType;
import pl.edu.agh.student.model.receipent.Recipient;
import pl.edu.agh.student.model.rule.RuleActionEntity;
import pl.edu.agh.student.model.rule.action.AbstractSendPushAction;
import pl.edu.agh.student.services.NotificationWriter;
import pl.edu.agh.student.services.external.DeviceService;

import java.util.UUID;

public abstract class AbstractSendNotificationExecutor implements ISpecificActionExecutor {

    private NotificationWriter notificationWriter;
    private DeviceService deviceService;

    public AbstractSendNotificationExecutor(NotificationWriter notificationWriter, DeviceService deviceService) {
        this.notificationWriter = notificationWriter;
        this.deviceService = deviceService;
    }

    @Override
    public void execute(RuleActionEntity action, UserEvent userEvent) {

        AbstractSendPushAction config = (AbstractSendPushAction) action.getRuleAction().getConfig();

        NotificationSendRequest notificationSendRequest = buildNotificationSendRequest(action, userEvent, config);
        notificationWriter.send(notificationSendRequest);

    }

    private NotificationSendRequest buildNotificationSendRequest(RuleActionEntity action, UserEvent userEvent, AbstractSendPushAction config) {

        Notification notification = new Notification();
        notification.setId(action.getId());
        notification.setTitle(config.getTitle());
        notification.setText(config.getText());
        notification.setType(getNotificationType());

        Recipient recipient = new Recipient();
        recipient.setToken(deviceService.getDeviceToken(userEvent.getUuid()));
        recipient.setSystem(System.ANDROID);
        recipient.setDeviceId(UUID.fromString(userEvent.getUuid()));

        NotificationSendRequest notificationSendRequest = new NotificationSendRequest();
        notificationSendRequest.setRecipient(recipient);
        notificationSendRequest.setNotification(notification);

        return notificationSendRequest;
    }

    abstract NotificationType getNotificationType();

}
