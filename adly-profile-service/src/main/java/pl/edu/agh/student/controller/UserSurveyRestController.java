package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.student.model.survey.Survey;
import pl.edu.agh.student.services.survey.SurveyCrudService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profile/user-survey")
public class UserSurveyRestController {

    private SurveyCrudService service;

    @Autowired
    public UserSurveyRestController(SurveyCrudService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Survey> get(Principal principal) {
        return service.get(principal);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Survey add(@RequestBody Survey property, Principal principal) {
        return service.add(property, principal);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Survey> get(@PathVariable Integer id, Principal principal) throws Exception {
        return Optional.ofNullable(service.get(id, principal))
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseThrow(() -> new Exception("Survey with requested id does not exist"));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Survey update(@RequestBody Survey updatedItem, @PathVariable Integer id, Principal principal) {
        return service.update(updatedItem, id, principal);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id, Principal principal) {
        service.delete(id, principal);
    }

}