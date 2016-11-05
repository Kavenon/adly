package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.student.services.BeaconInfoService;

@RestController
@RequestMapping("/beacon/info")
public class BeaconInfoController {

    @Autowired
    private BeaconInfoService service;

    @RequestMapping(value = "/discover", method = RequestMethod.GET)
    public ResponseEntity<Object> getBeacon(@RequestParam(value = "uuid") String uuid,
                                            @RequestParam(value = "beaconId") Integer beaconId,
                                            @RequestParam(value = "since") Long since) throws Exception {
        return new ResponseEntity<>(service.getDiscoveredBeaconsTraceId(beaconId, uuid, since), HttpStatus.OK);
    }

}