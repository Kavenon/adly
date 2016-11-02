package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.Rule;

import java.util.List;

@Repository
public interface RuleRepository extends CrudRepository<Rule, Integer> {

    List<Rule> findByUserIdAndDeleted(Long userId, boolean deleted);

    Rule findByIdAndUserId(Integer id, Long userId);

}
