package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.PredefinedProperty;

@Repository
public interface PredefinedPropertyRepository extends CrudRepository<PredefinedProperty, Long> {

}
