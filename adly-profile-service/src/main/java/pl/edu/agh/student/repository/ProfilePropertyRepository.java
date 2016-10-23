package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.property.ProfileProperty;

import java.util.List;

@Repository
public interface ProfilePropertyRepository extends CrudRepository<ProfileProperty, Integer> {

    List<ProfileProperty> findByUserId(Long userId);

    void deleteByIdAndUserId(Integer id, Long userId);

    ProfileProperty findByIdAndUserId(Integer id, Long userId);

    List<ProfileProperty> findByUserIdAndDeleted(Long userId, boolean deleted);

    List<ProfileProperty> findByUserIdIsNull();

}
