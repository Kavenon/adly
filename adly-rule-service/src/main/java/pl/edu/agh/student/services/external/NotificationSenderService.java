package pl.edu.agh.student.services.external;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "adly-notification-sender-service")
public interface NotificationSenderService {

    @RequestMapping(method = RequestMethod.GET, value = "/notification/sent")
    boolean hasSentNotification(@RequestParam("deviceId") UUID deviceId, @RequestParam("since") Long sinceMillis);

}