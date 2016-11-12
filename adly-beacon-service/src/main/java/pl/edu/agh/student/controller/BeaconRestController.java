package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.student.model.Beacon;
import pl.edu.agh.student.services.BeaconService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beacon")
public class BeaconRestController {

    @Autowired
    private BeaconService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Beacon> getBeacons(Principal principal) {
        return service.getBeacons(principal);
    }

    @RequestMapping(value="/predefined", method = RequestMethod.GET)
    public List<Beacon> getBeaconsPredefined() {
        return service.getPredefinedBeacons();
    }

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public List<Beacon> getAllBeacons(Principal principal) {
        return service.getAllBeacons(principal);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Beacon addBeacon(@RequestBody Beacon beacon, Principal principal) {
        return service.addBeacon(beacon, principal);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Beacon> getBeacon(@PathVariable Integer id, Principal principal) throws Exception {
        return Optional.ofNullable(service.getBeacon(id, principal))
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseThrow(() -> new Exception("Beacon with requested id does not exist"));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Beacon updateBeacon(@RequestBody Beacon updatedItem, @PathVariable Integer id, Principal principal) {
        return service.updateBeacon(updatedItem, id, principal);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBeacon(@PathVariable Integer id, Principal principal) {
        service.deleteBeacon(id, principal);
    }

}