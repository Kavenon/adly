package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.student.model.BeaconDiscoverRequest;
import pl.edu.agh.student.services.BeaconDiscoverService;

@RestController
@RequestMapping("/beacon/discover")
public class BeaconDiscoverController {

    @Autowired
    private BeaconDiscoverService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> handleBeaconDiscovered(BeaconDiscoverRequest request) {

        try {
            service.discovered(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}