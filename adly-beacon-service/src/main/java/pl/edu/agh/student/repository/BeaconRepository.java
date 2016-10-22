package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.Beacon;

import java.util.List;

@Repository
public interface BeaconRepository extends CrudRepository<Beacon, Integer> {

    List<Beacon> findByUserId(Long userId);

    void deleteByIdAndUserId(Integer beaconId, Long userId);
}
