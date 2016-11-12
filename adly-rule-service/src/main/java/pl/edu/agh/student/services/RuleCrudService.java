package pl.edu.agh.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.student.exceptions.UnauthorizedAccessException;
import pl.edu.agh.student.model.rule.Rule;
import pl.edu.agh.student.repository.RuleRepository;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class RuleCrudService {

    private RuleRepository repository;
    private UserService userService;

    @Autowired
    public RuleCrudService(RuleRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    private Long getUserId(Principal principal) {
        return userService.getUserId(principal.getName());
    }

    public Rule add(Rule rule, Principal principal) {
        rule.setId(null);
        rule.setUserId(getUserId(principal));
        return repository.save(rule);
    }

    public Rule update(Rule updatedItem, Integer id, Principal principal) {

        Rule one = repository.findOne(updatedItem.getId());

        Long userId = getUserId(principal);
        if(!Objects.equals(userId, one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        updatedItem.setId(id);
        updatedItem.setUserId(userId);
        return repository.save(updatedItem);

    }

    public void delete(Integer id, Principal principal) {

        Rule one = repository.findOne(id);

        if(!Objects.equals(getUserId(principal), one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        one.setDeleted(true);
        repository.save(one);

    }

    public Rule get(Integer id, Principal principal) {
        return repository.findByIdAndUserId(id, getUserId(principal));
    }

    public List<Rule> get(Principal principal) {
        return repository.findByUserIdAndDeleted(getUserId(principal), false);
    }



}
