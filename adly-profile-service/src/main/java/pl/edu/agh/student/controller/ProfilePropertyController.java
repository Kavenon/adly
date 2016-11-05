package pl.edu.agh.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.student.model.property.IPropertyType;
import pl.edu.agh.student.services.property.UserPropertyService;

@RestController
@RequestMapping("/profile/property")
public class ProfilePropertyController {

    private UserPropertyService propertyService;

    @Autowired
    public ProfilePropertyController(UserPropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{propertyId}")
    public IPropertyType getPropertyTypeById(@PathVariable("propertyId") Integer propertyId){
        return propertyService.getPropertyTypeById(propertyId);
    }

}
