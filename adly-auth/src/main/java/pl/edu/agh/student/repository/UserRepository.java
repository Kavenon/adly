package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);

}
