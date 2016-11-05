package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.student.service.NotificationSentService;

import java.util.UUID;

@RestController
@RequestMapping("/notification/sent")
public class NotificationSentController {

    private NotificationSentService notificationSentService;

    @Autowired
    public NotificationSentController(NotificationSentService notificationSentService) {
        this.notificationSentService = notificationSentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public boolean hasSentNotificationToDevice(@RequestParam("deviceId") UUID deviceId, @RequestParam("since") Long sinceMillis){
        return notificationSentService.hasSentToDevice(deviceId, sinceMillis);
    }
}
