package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.student.model.Rule;
import pl.edu.agh.student.services.RuleCrudService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rule")
public class RuleRestController {

    private RuleCrudService service;

    @Autowired
    public RuleRestController(RuleCrudService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Rule> get() {
        return service.get();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Rule add(@RequestBody Rule property) {
        return service.add(property);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Rule> get(@PathVariable Integer id) throws Exception {
        return Optional.ofNullable(service.get(id))
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseThrow(() -> new Exception("Rule with requested id does not exist"));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Rule update(@RequestBody Rule updatedItem, @PathVariable Integer id) {
        return service.update(updatedItem, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}