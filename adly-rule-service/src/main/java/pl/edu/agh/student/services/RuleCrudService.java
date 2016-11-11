package pl.edu.agh.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.student.exceptions.UnauthorizedAccessException;
import pl.edu.agh.student.model.rule.Rule;
import pl.edu.agh.student.repository.RuleRepository;

import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class RuleCrudService {

    private RuleRepository repository;
    private OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public RuleCrudService(RuleRepository repository, @LoadBalanced OAuth2RestTemplate oAuth2RestTemplate) {
        this.repository = repository;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    private Long getUserId() {
        return oAuth2RestTemplate.getForObject("http://adly-auth/uaa/userId", Long.class);
    }

    public Rule add(Rule rule) {
        rule.setId(null);
        rule.setUserId(getUserId());
        return repository.save(rule);
    }

    public Rule update(Rule updatedItem, Integer id) {

        Rule one = repository.findOne(updatedItem.getId());

        if(!Objects.equals(getUserId(), one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        updatedItem.setId(id);
        updatedItem.setUserId(getUserId());
        return repository.save(updatedItem);

    }

    public void delete(Integer id) {

        Rule one = repository.findOne(id);

        if(!Objects.equals(getUserId(), one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        one.setDeleted(true);
        repository.save(one);

    }

    public Rule get(Integer id) {
        return repository.findByIdAndUserId(id, getUserId());
    }

    public List<Rule> get() {
        return repository.findByUserIdAndDeleted(getUserId(), false);
    }



}
