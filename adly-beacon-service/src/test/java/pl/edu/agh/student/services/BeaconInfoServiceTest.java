package pl.edu.agh.student.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.agh.student.model.BeaconDiscoverHistory;
import pl.edu.agh.student.repository.BeaconDiscoverHistoryRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BeaconInfoServiceTest {

    @Autowired
    private BeaconDiscoverHistoryRepository beaconDiscoverHistoryRepository;
    @Test
    public void getDiscoveredBeaconsTraceId() throws Exception {

        List<BeaconDiscoverHistory> beaconsTraceId = beaconDiscoverHistoryRepository.findBeaconsTraceId(UUID.fromString("f51cc89d-8fac-45c0-a9ad-6e8b09e444d1"), 1, new Date(0), new Date());

        System.out.println(beaconsTraceId);
    }

}