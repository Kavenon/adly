package pl.edu.agh.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.student.exceptions.UnauthorizedAccessException;
import pl.edu.agh.student.model.Beacon;
import pl.edu.agh.student.repository.BeaconRepository;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class BeaconService {

    private BeaconRepository beaconRepository;
    private UserService userService;

    @Autowired
    public BeaconService(BeaconRepository beaconRepository, UserService userService) {
        this.beaconRepository = beaconRepository;
        this.userService = userService;
    }

    public List<Beacon> getPredefinedBeacons() { return beaconRepository.findByUserIdIsNull(); }
    public List<Beacon> getAllBeacons(Principal principal) { return beaconRepository.findByUserIdOrUserIdIsNull(getUserId(principal)); }
    public List<Beacon> getBeacons(Principal principal) {
        return beaconRepository.findByUserId(getUserId(principal));
    }

    private Long getUserId(Principal principal) {
        return userService.getUserId(principal.getName());
    }

    public Beacon addBeacon(Beacon beacon, Principal principal) {
        beacon.setId(null);
        beacon.setUserId(getUserId(principal));
        return beaconRepository.save(beacon);
    }

    public Beacon updateBeacon(Beacon updatedItem, Integer id, Principal principal) {

        Beacon one = beaconRepository.findOne(updatedItem.getId());

        Long userId = getUserId(principal);
        if(!Objects.equals(userId, one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        updatedItem.setId(id);
        updatedItem.setUserId(userId);
        return beaconRepository.save(updatedItem);

    }

    public void deleteBeacon(Integer beaconId, Principal principal) {
        beaconRepository.deleteByIdAndUserId(beaconId, getUserId(principal));
    }

    public Beacon getBeacon(Integer id, Principal principal) {
        return beaconRepository.findByIdAndUserId(id, getUserId(principal));
    }
}
