package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.student.model.property.ProfileProperty;
import pl.edu.agh.student.services.property.PredefinedPropertyService;

import java.util.List;

@RestController
@RequestMapping("/profile/predefined-property")
public class PredefinedPropertyRestController {

    private PredefinedPropertyService service;

    @Autowired
    public PredefinedPropertyRestController(PredefinedPropertyService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ProfileProperty> getProperties() {
        return service.findAllPredefined();
    }

}