package pl.edu.agh.student.repository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.NotificationSent;
import pl.edu.agh.student.model.NotificationSentKey;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationSentRepository extends CrudRepository<NotificationSent, NotificationSentKey> {

    @Query("select * from notification_sent where device_id = ?0 and date > ?1")
    List<NotificationSent> hasSentToDevice(UUID deviceId, Date since);

}
