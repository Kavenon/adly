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

    @Autowired
    public UserProfileConditionChecker(DeviceService deviceService, ProfileService profileService) {
        this.deviceService = deviceService;
        this.profileService = profileService;
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
        String propertyValue = profileService.getPropertyValueForUuid(profileUuid, singleCheck.getPropertyId());

        if(propertyValue == null){
            LOG.info("Property id {0} not found for profile {1}, returning false", singleCheck.getPropertyId(), profileUuid);
            return false;
        }
        return PropertyMatcherFactory.getMatcher(propertyType).match(singleCheck,propertyValue);

    }
}
