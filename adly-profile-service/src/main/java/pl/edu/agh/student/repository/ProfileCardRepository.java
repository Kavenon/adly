package pl.edu.agh.student.repository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.ProfileCard;
import pl.edu.agh.student.model.ProfileCardKey;

import java.util.UUID;

@Repository
public interface ProfileCardRepository extends CrudRepository<ProfileCard, ProfileCardKey> {

    @Query("select * from profile_card where profile_id = ?0 and property_id = ?1")
    ProfileCard findByProfileIdAndPropertyId(UUID profileId, Integer propertyId);

}
