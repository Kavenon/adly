package pl.edu.agh.student.services.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.student.exceptions.UnauthorizedAccessException;
import pl.edu.agh.student.model.property.IPropertyType;
import pl.edu.agh.student.model.property.ProfileProperty;
import pl.edu.agh.student.repository.ProfilePropertyRepository;
import pl.edu.agh.student.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class UserPropertyService {

    private ProfilePropertyRepository repository;
    private UserService userService;

    @Autowired
    public UserPropertyService(ProfilePropertyRepository repository,UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    private Long getUserId(Principal principal) {
        return userService.getUserId(principal.getName());
    }

    public ProfileProperty add(ProfileProperty userProperty, Principal principal) {
        userProperty.setId(null);
        userProperty.setUserId(getUserId(principal));
        return repository.save(userProperty);
    }

    public ProfileProperty update(ProfileProperty updatedItem, Integer id, Principal principal) {

        ProfileProperty one = repository.findOne(updatedItem.getId());

        Long userId = getUserId(principal);
        if(!Objects.equals(userId, one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        updatedItem.setId(id);
        updatedItem.setUserId(userId);
        updatedItem.setType(one.getType());
        return repository.save(updatedItem);

    }

    public void delete(Integer id, Principal principal) {

        ProfileProperty one = repository.findOne(id);

        if(!Objects.equals(getUserId(principal), one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        one.setDeleted(true);
        repository.save(one);

    }

    public ProfileProperty get(Integer id, Principal principal) {
        return repository.findByIdAndUserId(id, getUserId(principal));
    }

    public List<ProfileProperty> get(Principal principal) {
        return repository.findByUserIdAndDeleted(getUserId(principal), false);
    }

    public IPropertyType getPropertyTypeById(Integer propertyId) {

        ProfileProperty one = repository.findOne(propertyId);
        if(one == null){
            return null;
        }
        return one.getType();

    }
}
