package pl.edu.agh.student.services.external;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "adly-beacon-service")
public interface BeaconService {

    @RequestMapping(method = RequestMethod.GET, value = "/beacon/info/discover")
    List<UUID> getDiscoveredBeaconsTraceId(@RequestParam(value = "uuid") String uuid,
                                           @RequestParam(value = "beaconId") Integer beaconId,
                                           @RequestParam(value = "since") Long since);

}