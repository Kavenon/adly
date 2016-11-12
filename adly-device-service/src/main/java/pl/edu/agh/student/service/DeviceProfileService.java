package pl.edu.agh.student.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.exceptions.DeviceNotFoundException;
import pl.edu.agh.student.model.Device;
import pl.edu.agh.student.repository.DeviceRepository;

import java.util.UUID;

@Component
public class DeviceProfileService {

    private DeviceRepository deviceRepository;

    @Autowired
    public DeviceProfileService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public String getDeviceProfileID(String deviceId) {

        UUID deviceUuid = UUID.fromString(deviceId);
        Device one = deviceRepository.findOne(deviceUuid);

        if(one != null){
            return one.getProfile().toString();
        }

        throw new DeviceNotFoundException();

    }

    public String getDeviceToken(String deviceId) {
        UUID deviceUuid = UUID.fromString(deviceId);
        Device one = deviceRepository.findOne(deviceUuid);

        if(one != null){
            return one.getToken();
        }

        throw new DeviceNotFoundException();
    }
}
