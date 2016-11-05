package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.rule.RuleActionEntity;

@Repository
public interface RuleActionRepository extends CrudRepository<RuleActionEntity, Integer> {

}
