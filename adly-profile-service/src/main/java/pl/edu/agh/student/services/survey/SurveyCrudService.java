package pl.edu.agh.student.services.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.student.exceptions.UnauthorizedAccessException;
import pl.edu.agh.student.model.survey.Survey;
import pl.edu.agh.student.repository.UserSurveyRepository;
import pl.edu.agh.student.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class SurveyCrudService {

    private UserSurveyRepository repository;
    private UserService userService;

    @Autowired
    public SurveyCrudService(UserSurveyRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    private Long getUserId(Principal principal) {
        return userService.getUserId(principal.getName());
    }

    public Survey add(Survey userSurvey, Principal principal) {
        userSurvey.setId(null);
        userSurvey.setUserId(getUserId(principal));
        return repository.save(userSurvey);
    }

    public Survey update(Survey updatedItem, Integer id, Principal principal) {

        Survey one = repository.findOne(updatedItem.getId());

        Long userId = getUserId(principal);
        if(!Objects.equals(userId, one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        updatedItem.setId(id);
        updatedItem.setUserId(userId);
        return repository.save(updatedItem);

    }

    public void delete(Integer id, Principal principal) {

        Survey one = repository.findOne(id);

        if(!Objects.equals(getUserId(principal), one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        one.setDeleted(true);
        repository.save(one);

    }

    public Survey get(Integer id, Principal principal) {
        return repository.findByIdAndUserId(id, getUserId(principal));
    }

    public List<Survey> get(Principal principal) {
        return repository.findByUserIdAndDeleted(getUserId(principal), false);
    }



}
