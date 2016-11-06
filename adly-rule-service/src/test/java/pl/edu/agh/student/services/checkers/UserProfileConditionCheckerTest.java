package pl.edu.agh.student.services.checkers;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.student.model.rule.condition.UserProfileCondition;
import pl.edu.agh.student.model.rule.condition.UserProfileConditionCheck;
import pl.edu.agh.student.model.rule.condition.UserProfileConditionConfig;
import pl.edu.agh.student.model.rule.property.type.IPropertyType;
import pl.edu.agh.student.services.MockUserEvent;
import pl.edu.agh.student.services.external.DeviceService;
import pl.edu.agh.student.services.external.ProfileService;
import pl.edu.agh.student.services.property.IPropertyMatcher;
import pl.edu.agh.student.services.property.PropertyMatcherFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserProfileConditionCheckerTest  {

    private UserProfileConditionChecker checker;
    private ProfileService profileService;
    private IPropertyMatcher propertyMatcher;
    @Before
    public void setUp() throws Exception {

        DeviceService deviceService = mock(DeviceService.class);
        when(deviceService.getProfileId(any())).thenReturn(UUID.randomUUID().toString());
        PropertyMatcherFactory propertyMatcherFactory = mock(PropertyMatcherFactory.class);

        profileService = mock(ProfileService.class);
        propertyMatcher = mock(IPropertyMatcher.class);
        when(propertyMatcherFactory.getMatcher(any())).thenReturn(propertyMatcher);

        checker = new UserProfileConditionChecker(deviceService,profileService, propertyMatcherFactory);

    }

    @Test
    public void checkNotExistingProperty() throws Exception {

        when(profileService.getPropertyTypeById(any())).thenReturn(null);

        boolean check = checker.check(getCondition(),new MockUserEvent());
        assertThat(check).isFalse();

    }

    @Test
    public void checkNotExistingPropertyValue() throws Exception {

        when(profileService.getPropertyTypeById(any())).thenReturn(mock(IPropertyType.class));
        when(profileService.getPropertyValueForUuid(any(),any())).thenReturn(null);

        boolean check = checker.check(getCondition(),new MockUserEvent());
        assertThat(check).isFalse();

    }


    @Test
    public void checkMatcherTrue() throws Exception {

        when(profileService.getPropertyTypeById(any())).thenReturn(mock(IPropertyType.class));
        when(profileService.getPropertyValueForUuid(any(),any())).thenReturn("");
        when(propertyMatcher.match(any(),any())).thenReturn(true);

        boolean check = checker.check(getCondition(),new MockUserEvent());
        assertThat(check).isTrue();

    }


    @Test
    public void checkMatcherFalse() throws Exception {

        when(profileService.getPropertyTypeById(any())).thenReturn(mock(IPropertyType.class));
        when(profileService.getPropertyValueForUuid(any(),any())).thenReturn("");
        when(propertyMatcher.match(any(),any())).thenReturn(false);

        boolean check = checker.check(getCondition(),new MockUserEvent());
        assertThat(check).isFalse();

    }

    @Test
    public void checkWithoutCheckers() throws Exception {

        UserProfileCondition condition = getCondition();
        condition.getConfig().setChecks(new ArrayList<>());
        boolean check = checker.check(condition,new MockUserEvent());
        assertThat(check).isTrue();

    }


    private UserProfileCondition getCondition() {
        UserProfileConditionConfig config = new UserProfileConditionConfig();
        config.setChecks(Collections.singletonList(new UserProfileConditionCheck()));
        UserProfileCondition condition = new UserProfileCondition();
        condition.setConfig(config);
        return condition;
    }


}