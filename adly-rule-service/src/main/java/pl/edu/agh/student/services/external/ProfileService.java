package pl.edu.agh.student.services.external;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.agh.student.model.rule.property.type.IPropertyType;
import pl.edu.agh.student.model.survey.Survey;

import java.util.UUID;

@FeignClient(name = "adly-profile-service")
public interface ProfileService {

    @RequestMapping(method = RequestMethod.GET, value = "/device/profile")
    String getProfileId(@RequestParam("deviceId") String deviceId);

    @RequestMapping(method = RequestMethod.GET, value = "/survey/client")
    Survey getClientSurvey(@RequestParam("id") Integer surveyId);

    // todo : add cache (short)
    @RequestMapping(method = RequestMethod.GET, value = "/profile/card/{propertyId}")
    String getPropertyValueForUuid(@RequestParam("profileUuid") UUID profileUuid, @PathVariable("propertyId") Integer propertyId);

    @RequestMapping(method = RequestMethod.GET, value = "/profile/property/{propertyId}")
    IPropertyType getPropertyTypeById(@PathVariable("propertyId") Integer propertyId);
}