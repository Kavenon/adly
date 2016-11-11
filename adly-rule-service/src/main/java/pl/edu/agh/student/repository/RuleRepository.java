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

    @Query(value = "select * from rule where events \\:\\: jsonb @> cast(?3 as jsonb) and active = ?2 and user_id = ?1 ", nativeQuery = true)
    List<Rule> findByUserIdAndActiveAndEventType(Long userId, boolean active, String eventType);

    @Query(value = "select * from rule where events \\:\\: jsonb @> cast(?2 as jsonb) and active = ?1 ", nativeQuery = true)
    List<Rule> findByActiveAndEventType(boolean b, String s);
}
