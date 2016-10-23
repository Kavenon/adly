package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.student.model.survey.UserSurvey;
import pl.edu.agh.student.services.survey.UserSurveyService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profile/user-survey")
public class UserSurveyRestController {

    private UserSurveyService service;

    @Autowired
    public UserSurveyRestController(UserSurveyService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserSurvey> get() {
        return service.get();
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserSurvey add(@RequestBody UserSurvey property) {
        return service.add(property);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserSurvey> get(@PathVariable Integer id) throws Exception {
        return Optional.ofNullable(service.get(id))
                .map(a -> new ResponseEntity<>(a, HttpStatus.OK))
                .orElseThrow(() -> new Exception("Survey with requested id does not exist"));
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public UserSurvey update(@RequestBody UserSurvey updatedItem, @PathVariable Integer id) {
        return service.update(updatedItem, id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}