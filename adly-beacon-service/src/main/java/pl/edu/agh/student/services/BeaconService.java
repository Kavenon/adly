package pl.edu.agh.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.student.exceptions.UnauthorizedAccessException;
import pl.edu.agh.student.model.Beacon;
import pl.edu.agh.student.repository.BeaconRepository;

import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class BeaconService {

    private BeaconRepository beaconRepository;
    private OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public BeaconService(BeaconRepository beaconRepository, OAuth2RestTemplate oAuth2RestTemplate) {
        this.beaconRepository = beaconRepository;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    public List<Beacon> getBeacons() {
        return beaconRepository.findByUserId(getUserId());
    }

    private Long getUserId() {
        return oAuth2RestTemplate.getForObject("http://localhost:9191/uaa/userId", Long.class);
    }

    public Beacon addBeacon(Beacon beacon) {
        beacon.setId(null);
        beacon.setUserId(getUserId());
        return beaconRepository.save(beacon);
    }

    public Beacon updateBeacon(Beacon updatedItem, Integer id) {

        Beacon one = beaconRepository.findOne(updatedItem.getId());

        if(!Objects.equals(getUserId(), one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        updatedItem.setId(id);
        updatedItem.setUserId(getUserId());
        return beaconRepository.save(updatedItem);

    }

    public void deleteBeacon(Integer beaconId) {
        beaconRepository.deleteByIdAndUserId(beaconId, getUserId());
    }

    public Beacon getBeacon(Integer id) {
        return beaconRepository.findByIdAndUserId(id, getUserId());
    }
}
