package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/device")
public class DeviceProfileController {

    @Autowired
    private DeviceProfileService service;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<Object> getBeacons(@RequestParam String deviceId) {

        try {
            return new ResponseEntity<>(service.getDeviceProfileID(deviceId), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    public ResponseEntity<Object> getToken(@RequestParam String deviceId) {

        try {
            return new ResponseEntity<>(service.getDeviceToken(deviceId), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}