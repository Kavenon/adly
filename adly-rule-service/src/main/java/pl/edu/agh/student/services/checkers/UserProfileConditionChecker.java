package pl.edu.agh.student.services.checkers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.event.UserEvent;
import pl.edu.agh.student.model.rule.condition.RuleCondition;
import pl.edu.agh.student.model.rule.condition.UserProfileCondition;
import pl.edu.agh.student.model.rule.condition.UserProfileConditionCheck;
import pl.edu.agh.student.model.rule.property.type.IPropertyType;
import pl.edu.agh.student.services.external.DeviceService;
import pl.edu.agh.student.services.external.ProfileService;
import pl.edu.agh.student.services.property.PropertyMatcherFactory;

import java.util.UUID;

@Component
public class UserProfileConditionChecker implements ISpecificConditionChecker {

    private static final Logger LOG = LoggerFactory.getLogger(UserProfileConditionCheck.class);
    private DeviceService deviceService;
    private ProfileService profileService;
    private PropertyMatcherFactory propertyMatcherFactory;

    @Autowired
    public UserProfileConditionChecker(DeviceService deviceService, ProfileService profileService, PropertyMatcherFactory propertyMatcherFactory) {
        this.deviceService = deviceService;
        this.profileService = profileService;
        this.propertyMatcherFactory = propertyMatcherFactory;
    }

    @Override
    public boolean check(RuleCondition _condition, UserEvent userEvent) {

        UserProfileCondition condition = (UserProfileCondition) _condition;

        UUID profileUuid = UUID.fromString(deviceService.getProfileId(userEvent.getUuid()));

        return condition.getConfig()
                .getChecks()
                .stream()
                .allMatch(singleCheck -> testSingleCheck(singleCheck, profileUuid));
    }

    private boolean testSingleCheck(UserProfileConditionCheck singleCheck, UUID profileUuid) {

        IPropertyType propertyType = profileService.getPropertyTypeById(singleCheck.getPropertyId());

        if(propertyType == null){
            LOG.info("Property id {0} not found, returning false", singleCheck.getPropertyId());
            return false;
        }

        String propertyValue = profileService.getPropertyValueForUuid(profileUuid, singleCheck.getPropertyId());

        if(propertyValue == null){
            LOG.info("Property id {0} not found for profile {1}, returning false", singleCheck.getPropertyId(), profileUuid);
            return false;
        }

        boolean match = propertyMatcherFactory.getMatcher(propertyType).match(singleCheck, propertyValue);

        if(!match){
            LOG.info("Profile property not matched, expected " + singleCheck + " but was: " + propertyValue);
        }

        return match;

    }

}
