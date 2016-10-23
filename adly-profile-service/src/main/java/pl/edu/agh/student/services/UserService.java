package pl.edu.agh.student.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("http://localhost:9191")
public interface UserService {

    @RequestMapping(method = RequestMethod.GET, value = "/uaa/userId")
    Long getUserId();

}