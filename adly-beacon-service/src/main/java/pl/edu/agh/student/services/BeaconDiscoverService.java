package pl.edu.agh.student.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.BeaconDiscoverEvent;
import pl.edu.agh.student.BeaconWriter;
import pl.edu.agh.student.model.Beacon;
import pl.edu.agh.student.model.BeaconDiscoverHistory;
import pl.edu.agh.student.model.BeaconDiscoverRequest;
import pl.edu.agh.student.model.UuidAndDateKey;
import pl.edu.agh.student.repository.BeaconDiscoverHistoryRepository;
import pl.edu.agh.student.repository.BeaconRepository;

import java.util.Date;
import java.util.UUID;

@Component
public class BeaconDiscoverService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private BeaconRepository beaconRepository;
    private BeaconDiscoverHistoryRepository beaconDiscoverHistoryRepository;
    private BeaconWriter beaconWriter;

    @Autowired
    public BeaconDiscoverService(BeaconRepository beaconRepository, BeaconDiscoverHistoryRepository beaconDiscoverHistoryRepository, BeaconWriter beaconWriter) {
        this.beaconRepository = beaconRepository;
        this.beaconDiscoverHistoryRepository = beaconDiscoverHistoryRepository;
        this.beaconWriter = beaconWriter;
    }

    public void discovered(BeaconDiscoverRequest request) {

        Beacon beacon = beaconRepository.findByMinorAndMajorAndUidAndPuuid(request.getMinor(), request.getMajor(), request.getUid(), request.getPuuid());

        if(beacon == null){
            LOG.info("Discovered beacon that is not mapped {}", request.toString());
            return ;
        }

        UUID traceId = UUID.randomUUID();
        saveBeaconDiscoverHistory(request, beacon, traceId);
        publishBeaconDiscoverEvent(request, beacon, traceId);

        LOG.info("Discovered beacon {}", request.toString());

    }

    private void saveBeaconDiscoverHistory(BeaconDiscoverRequest request, Beacon beacon, UUID traceId) {
        UuidAndDateKey key = new UuidAndDateKey(request.getUuid(), beacon.getId());
        BeaconDiscoverHistory beaconDiscoverHistory = new BeaconDiscoverHistory(
                key,
                new Date(),
                traceId);
        beaconDiscoverHistoryRepository.save(beaconDiscoverHistory);
    }

    private void publishBeaconDiscoverEvent(BeaconDiscoverRequest request, Beacon beacon, UUID traceId) {
        beaconWriter.write(new BeaconDiscoverEvent(request.getUuid().toString(),traceId.toString(),beacon));
    }

}
