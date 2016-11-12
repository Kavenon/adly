package pl.edu.agh.student.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.student.service.DeviceProfileService;

@RestController
@RequestMapping("/device")
public class DeviceProfileController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeviceProfileService service;

    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<Object> getBeacons(@RequestParam String deviceId) {

        try {
            return new ResponseEntity<>(service.getDeviceProfileID(deviceId), HttpStatus.OK);
        }
        catch(Exception e){
            log.error("Error during profile fetch", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    public ResponseEntity<Object> getToken(@RequestParam String deviceId) {

        try {
            return new ResponseEntity<>(service.getDeviceToken(deviceId), HttpStatus.OK);
        }
        catch(Exception e){
            log.error("Error during get token fetch", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}