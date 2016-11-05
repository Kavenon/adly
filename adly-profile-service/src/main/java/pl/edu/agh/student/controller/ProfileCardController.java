package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.student.services.ProfileCardService;

import java.util.UUID;

@RestController
@RequestMapping("/profile/card")
public class ProfileCardController {

    private ProfileCardService profileCardService;

    @Autowired
    public ProfileCardController(ProfileCardService profileCardService) {
        this.profileCardService = profileCardService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{propertyId}")
    public String getPropertyValueForUuid(@RequestParam("profileUuid") UUID profileUuid, @PathVariable("propertyId") Integer propertyId){
        return profileCardService.getPropertyValueForUuid(profileUuid, propertyId);
    }

}
