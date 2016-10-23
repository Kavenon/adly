package pl.edu.agh.student.services.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.student.exceptions.UnauthorizedAccessException;
import pl.edu.agh.student.model.property.UserProperty;
import pl.edu.agh.student.repository.UserPropertyRepository;

import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class UserPropertyService {

    private UserPropertyRepository repository;
    private OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public UserPropertyService(UserPropertyRepository repository, OAuth2RestTemplate oAuth2RestTemplate) {
        this.repository = repository;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    private Long getUserId() {
        return oAuth2RestTemplate.getForObject("http://localhost:9191/uaa/userId", Long.class);
    }

    public UserProperty add(UserProperty userProperty) {
        userProperty.setId(null);
        userProperty.setUserId(getUserId());
        return repository.save(userProperty);
    }

    public UserProperty update(UserProperty updatedItem, Integer id) {

        UserProperty one = repository.findOne(updatedItem.getId());

        if(!Objects.equals(getUserId(), one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        updatedItem.setId(id);
        updatedItem.setUserId(getUserId());
        updatedItem.setType(one.getType());
        return repository.save(updatedItem);

    }

    public void delete(Integer id) {

        UserProperty one = repository.findOne(id);

        if(!Objects.equals(getUserId(), one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        one.setDeleted(true);
        repository.save(one);

    }

    public UserProperty get(Integer id) {
        return repository.findByIdAndUserId(id, getUserId());
    }

    public List<UserProperty> get() {
        return repository.findByUserIdAndDeleted(getUserId(), false);
    }
}
