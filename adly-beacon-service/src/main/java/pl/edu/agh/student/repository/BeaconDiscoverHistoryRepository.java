package pl.edu.agh.student.repository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.BeaconDiscoverHistory;
import pl.edu.agh.student.model.UuidAndDateKey;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface BeaconDiscoverHistoryRepository extends CrudRepository<BeaconDiscoverHistory, UuidAndDateKey> {

    @Query("select * from beacon_discover_history where uuid = ?0 and beacon_id = ?1 and date > ?2 and date < ?3")
    List<BeaconDiscoverHistory> findBeaconsTraceId(UUID uuid, Integer beaconId, Date sinceDate, Date untilDate);
}
