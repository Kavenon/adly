package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.student.model.Beacon;
import pl.edu.agh.student.services.BeaconService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beacon")
public class BeaconRestController {

    @Autowired
    private BeaconService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Beacon> getBeacons() {
        return service.getBeacons();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Beacon addBeacon(@RequestBody Beacon beacon) {
        return service.addBeacon(beacon);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Beacon> getBeacon(@PathVariable Integer id) throws Exception {
        return Optional.ofNullable(service.getBeacon(id))
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseThrow(() -> new Exception("Beacon with requested id does not exist"));
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