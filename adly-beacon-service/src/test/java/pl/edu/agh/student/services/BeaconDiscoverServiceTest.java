package pl.edu.agh.student.services;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.student.BeaconWriter;
import pl.edu.agh.student.model.BeaconDiscoverHistory;
import pl.edu.agh.student.model.BeaconDiscoverRequest;
import pl.edu.agh.student.repository.BeaconDiscoverHistoryRepository;
import pl.edu.agh.student.repository.BeaconRepository;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class BeaconDiscoverServiceTest {

    private BeaconRepository beaconRepository;
    private BeaconDiscoverHistoryRepository beaconDiscoverHistoryRepository;
    private BeaconWriter beaconWriter;
    private BeaconDiscoverService service;

    @Before
    public void setUp(){
        beaconDiscoverHistoryRepository = mock(BeaconDiscoverHistoryRepository.class);
        beaconRepository = mock(BeaconRepository.class);
        beaconWriter = mock(BeaconWriter.class);
        service = new BeaconDiscoverService(beaconRepository,beaconDiscoverHistoryRepository, beaconWriter);
    }

    @Test
    public void discoveredBeaconNotMapped() throws Exception {

        when(beaconRepository.findByMinorAndMajorAndUidAndPuuid(any(), any(), any(), any())).thenReturn(null);
        service.discovered(new BeaconDiscoverRequest());
        verify(beaconDiscoverHistoryRepository,times(0)).save(any(BeaconDiscoverHistory.class));

    }

}