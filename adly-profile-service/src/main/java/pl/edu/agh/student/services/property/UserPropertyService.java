package pl.edu.agh.student.services.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.student.exceptions.UnauthorizedAccessException;
import pl.edu.agh.student.model.property.IPropertyType;
import pl.edu.agh.student.model.property.ProfileProperty;
import pl.edu.agh.student.repository.ProfilePropertyRepository;

import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class UserPropertyService {

    private ProfilePropertyRepository repository;
    private OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public UserPropertyService(ProfilePropertyRepository repository, @LoadBalanced  OAuth2RestTemplate oAuth2RestTemplate) {
        this.repository = repository;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    private Long getUserId() {
        return oAuth2RestTemplate.getForObject("http://adly-auth/uaa/userId", Long.class);
    }

    public ProfileProperty add(ProfileProperty userProperty) {
        userProperty.setId(null);
        userProperty.setUserId(getUserId());
        return repository.save(userProperty);
    }

    public ProfileProperty update(ProfileProperty updatedItem, Integer id) {

        ProfileProperty one = repository.findOne(updatedItem.getId());

        if(!Objects.equals(getUserId(), one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        updatedItem.setId(id);
        updatedItem.setUserId(getUserId());
        updatedItem.setType(one.getType());
        return repository.save(updatedItem);

    }

    public void delete(Integer id) {

        ProfileProperty one = repository.findOne(id);

        if(!Objects.equals(getUserId(), one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        one.setDeleted(true);
        repository.save(one);

    }

    public ProfileProperty get(Integer id) {
        return repository.findByIdAndUserId(id, getUserId());
    }

    public List<ProfileProperty> get() {
        return repository.findByUserIdAndDeleted(getUserId(), false);
    }

    public IPropertyType getPropertyTypeById(Integer propertyId) {

        ProfileProperty one = repository.findOne(propertyId);
        if(one == null){
            return null;
        }
        return one.getType();

    }
}
