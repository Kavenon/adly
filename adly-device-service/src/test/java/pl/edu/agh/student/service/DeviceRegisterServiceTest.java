package pl.edu.agh.student.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import pl.edu.agh.student.exceptions.UuidAlreadyRegisteredException;
import pl.edu.agh.student.model.Device;
import pl.edu.agh.student.model.Profile;
import pl.edu.agh.student.model.System;
import pl.edu.agh.student.repository.DeviceRepository;
import pl.edu.agh.student.repository.ProfileRepository;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class DeviceRegisterServiceTest {

    private DeviceRepository deviceRepository;
    private ProfileRepository profileRepository;
    private DeviceRegisterService deviceRegisterService;

    @Before
    public void setUp(){
        deviceRepository = mock(DeviceRepository.class);
        profileRepository = mock(ProfileRepository.class);
        deviceRegisterService  = new DeviceRegisterService(profileRepository,deviceRepository);
    }
    @Test
    public void register() throws Exception {

        UUID deviceUuid = UUID.randomUUID();
        System system = System.ANDROID;

        deviceRegisterService.register(deviceUuid.toString(), system);

        ArgumentCaptor<Profile> profileCaptor = ArgumentCaptor.forClass(Profile.class);
        verify(profileRepository).save(profileCaptor.capture());

        ArgumentCaptor<Device> deviceArgumentCaptor = ArgumentCaptor.forClass(Device.class);
        verify(deviceRepository).save(deviceArgumentCaptor.capture());

        assertThat(profileCaptor.getValue().getDevices().size()).isEqualTo(1);
        assertThat(profileCaptor.getValue().getDevices().get(0)).isEqualTo(deviceUuid);

        assertThat(deviceArgumentCaptor.getValue().getProfile()).isEqualTo(profileCaptor.getValue().getUuid());
        assertThat(deviceArgumentCaptor.getValue().getUuid()).isEqualTo(deviceUuid);
        assertThat(deviceArgumentCaptor.getValue().getSystem()).isEqualTo(system.name());

    }

    @Test
    public void registerSameUuid() throws Exception {

        UUID deviceUuid = UUID.randomUUID();
        when(deviceRepository.findOne(deviceUuid)).thenReturn(new Device());

        try {
            deviceRegisterService.register(deviceUuid.toString(), System.ANDROID);
            fail();
        }
        catch(UuidAlreadyRegisteredException ignored){}

    }

    @Test
    public void tokenWithExistingDevice() throws Exception {

        UUID deviceUuid = UUID.randomUUID();
        when(deviceRepository.findOne(deviceUuid)).thenReturn(new Device(deviceUuid, UUID.randomUUID(),System.ANDROID));

        deviceRegisterService.token(deviceUuid.toString(), "token");

        verify(deviceRepository, times(1)).save(any(Device.class));

    }

    @Test
    public void tokenWithNonDevice() throws Exception {

        UUID deviceUuid = UUID.randomUUID();
        when(deviceRepository.findOne(deviceUuid)).thenReturn(null);

        deviceRegisterService.token(deviceUuid.toString(), "token");

        verify(deviceRepository, times(0)).save(any(Device.class));

    }


}