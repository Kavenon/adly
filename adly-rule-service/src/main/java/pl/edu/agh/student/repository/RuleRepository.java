package pl.edu.agh.student.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.rule.Rule;

import java.util.List;

@Repository
public interface RuleRepository extends CrudRepository<Rule, Integer> {

    List<Rule> findByUserIdAndDeleted(Long userId, boolean deleted);

    Rule findByIdAndUserId(Integer id, Long userId);

    @Query(value = "select * from rule where events :: jsonb @> '[{\"type\":\"?2\"}]' and active = ?1 and user_id = ?0")
    List<Rule> findByUserIdAndActiveAndEventType(Long userId, boolean active, String eventType);

}
