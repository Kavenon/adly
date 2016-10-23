package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.UserProperty;

import java.util.List;

@Repository
public interface UserPropertyRepository extends CrudRepository<UserProperty, Integer> {

    List<UserProperty> findByUserId(Long userId);

    void deleteByIdAndUserId(Integer id, Long userId);

    UserProperty findByIdAndUserId(Integer id, Long userId);

    List<UserProperty> findByUserIdAndDeleted(Long userId, boolean deleted);

}
