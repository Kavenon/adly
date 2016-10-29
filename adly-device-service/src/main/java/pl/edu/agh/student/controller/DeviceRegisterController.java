package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.student.model.System;
import pl.edu.agh.student.service.DeviceRegisterService;

@RestController
@RequestMapping("/device")
public class DeviceRegisterController {

    @Autowired
    private DeviceRegisterService service;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ResponseEntity<Object> getBeacons(@RequestParam String uuid) {

        // todo: add system to request params
        try {
            service.register(uuid, System.ANDROID);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public ResponseEntity<Object> getBeacons(@RequestParam String uuid,@RequestParam String token) {

        try {
            service.token(uuid, token);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}