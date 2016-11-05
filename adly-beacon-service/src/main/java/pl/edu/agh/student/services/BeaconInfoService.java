package pl.edu.agh.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.BeaconDiscoverHistory;
import pl.edu.agh.student.repository.BeaconDiscoverHistoryRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BeaconInfoService {

    private BeaconDiscoverHistoryRepository beaconDiscoverHistoryRepository;

    @Autowired
    public BeaconInfoService(BeaconDiscoverHistoryRepository beaconDiscoverHistoryRepository) {
        this.beaconDiscoverHistoryRepository = beaconDiscoverHistoryRepository;
    }

    public List<UUID> getDiscoveredBeaconsTraceId(Integer beaconId, String uuid, Long since) {
        Date untilDate = new Date();
        Date sinceDate = new Date(since);
        return beaconDiscoverHistoryRepository.findBeaconsTraceId(UUID.fromString(uuid), beaconId, sinceDate, untilDate)
                .stream()
                .map(BeaconDiscoverHistory::getTraceId)
                .collect(Collectors.toList());
    }

}
