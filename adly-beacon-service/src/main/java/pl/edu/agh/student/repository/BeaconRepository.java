package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.Beacon;

import java.util.List;

@Repository
public interface BeaconRepository extends CrudRepository<Beacon, Integer> {

    List<Beacon> findByUserId(Long userId);

    void deleteByIdAndUserId(Integer beaconId, Long userId);

    Beacon findByIdAndUserId(Integer id, Long userId);

    Beacon findByMinorAndMajorAndUidAndPuuid(Integer minor, Integer major, String uid, String proximityUuid);

    List<Beacon> findByUserIdIsNull();

    List<Beacon> findByUserIdOrUserIdIsNull(Long userId);
}
