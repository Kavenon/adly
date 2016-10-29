package pl.edu.agh.student.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.exceptions.UuidAlreadyRegisteredException;
import pl.edu.agh.student.model.Device;
import pl.edu.agh.student.model.Profile;
import pl.edu.agh.student.model.System;
import pl.edu.agh.student.repository.DeviceRepository;
import pl.edu.agh.student.repository.ProfileRepository;

import java.util.UUID;

@Component
public class DeviceRegisterService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private ProfileRepository profileRepository;
    private DeviceRepository deviceRepository;

    @Autowired
    public DeviceRegisterService(ProfileRepository profileRepository, DeviceRepository deviceRepository) {
        this.profileRepository = profileRepository;
        this.deviceRepository = deviceRepository;
    }

    public void register(String uuid, System system) {

        UUID deviceUuid = UUID.fromString(uuid);

        Device one = deviceRepository.findOne(deviceUuid);
        if(one != null){
            throw new UuidAlreadyRegisteredException();
        }

        Profile profile = new Profile();
                profile.addDevice(deviceUuid);

        Device device = new Device(deviceUuid, profile.getUuid(), system);

        profileRepository.save(profile);
        deviceRepository.save(device);

        LOG.info("Registered device with uuid {0} and system {1}", uuid, system.name());

    }

    public void token(String uuid, String token) {

        UUID deviceUuid = UUID.fromString(uuid);

        Device one = deviceRepository.findOne(deviceUuid);
        if(one != null){
            one.setToken(token);
            deviceRepository.save(one);

            LOG.info("Updated token for device with uuid {0}", uuid);
        }
        else {
            LOG.warn("Could not find device with uuid {0}", uuid);
        }

    }
}
