package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.BeaconDiscoverHistory;
import pl.edu.agh.student.model.UuidAndDateKey;

@Repository
public interface BeaconDiscoverHistoryRepository extends CrudRepository<BeaconDiscoverHistory, UuidAndDateKey> {

}
