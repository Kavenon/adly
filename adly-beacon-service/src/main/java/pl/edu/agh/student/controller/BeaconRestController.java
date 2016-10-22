package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.student.model.Beacon;
import pl.edu.agh.student.services.BeaconService;

import java.util.List;

@RestController
@RequestMapping("/beacon")
public class BeaconRestController {

    @Autowired
    private BeaconService service;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getBetetacons() {
        return "test";
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Beacon> getBeacons() {
        return service.getBeacons();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Beacon addBeacon(@RequestBody Beacon beacon) {
        return service.addBeacon(beacon);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Beacon updateBeacon(@RequestBody Beacon updatedItem, @PathVariable Integer id) {
        return service.updateBeacon(updatedItem, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBeacon(@PathVariable Integer id) {
        service.deleteBeacon(id);
    }

}