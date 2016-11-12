package pl.edu.agh.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
import pl.edu.agh.student.model.BeaconDiscoverEvent;
import pl.edu.agh.student.model.Device;
import pl.edu.agh.student.model.VendorAndProfileKey;
import pl.edu.agh.student.model.VendorProfiles;
import pl.edu.agh.student.repository.DeviceRepository;
import pl.edu.agh.student.repository.VendorProfilesRepository;

import java.util.UUID;

@MessageEndpoint
public class BeaconDiscoverProcessor {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private DeviceRepository deviceRepository;
    private VendorProfilesRepository vendorProfilesRepository;

    @Autowired
    public BeaconDiscoverProcessor(DeviceRepository deviceRepository, VendorProfilesRepository vendorProfilesRepository) {
        this.deviceRepository = deviceRepository;
        this.vendorProfilesRepository = vendorProfilesRepository;
    }

    @StreamListener("input")
    public void onBeaconDiscover(BeaconDiscoverEvent event){

        if(event.getBeacon().getUserId() == null){
            LOG.info("Discovered predefined beacon, skipping...");
            return ;
        }

        UUID deviceUuid = UUID.fromString(event.getUuid());
        Device byUuid = deviceRepository.findOne(deviceUuid);
        if(byUuid != null){

            vendorProfilesRepository.save(
                    new VendorProfiles(
                            new VendorAndProfileKey(
                                    event.getBeacon().getUserId().intValue(),
                                    byUuid.getProfile()
                            )
                    )
            );
            LOG.info("Received beacon discover event " + event);
        }
        else {
            LOG.warn("Received beacon discover event, for not registered device " + event);
        }

	}
}