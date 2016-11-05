package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.student.services.RuleActionService;

@RestController
public class RuleActionPayloadController {

    private RuleActionService service;

    @Autowired
    public RuleActionPayloadController(RuleActionService service) {
        this.service = service;
    }

    @RequestMapping(value = "/notification/payload", method = RequestMethod.GET)
    public Object getPayload(@RequestParam("_anid") Integer id) {
        return service.getActionPayload(id);
    }

}