package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.student.model.property.ProfileProperty;
import pl.edu.agh.student.services.property.UserPropertyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profile/user-property")
public class UserPropertyRestController {

    private UserPropertyService service;

    @Autowired
    public UserPropertyRestController(UserPropertyService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ProfileProperty> get() {
        return service.get();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ProfileProperty add(@RequestBody ProfileProperty property) {
        return service.add(property);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProfileProperty> get(@PathVariable Integer id) throws Exception {
        return Optional.ofNullable(service.get(id))
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseThrow(() -> new Exception("Propety with requested id does not exist"));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ProfileProperty update(@RequestBody ProfileProperty updatedItem, @PathVariable Integer id) {
        return service.update(updatedItem, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }


}