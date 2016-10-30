package pl.edu.agh.student.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "adly-device-service")
public interface ProfileService {

    @RequestMapping(method = RequestMethod.GET, value = "/device/profile")
    String getProfileId(@RequestParam("deviceId") String deviceId);

}
