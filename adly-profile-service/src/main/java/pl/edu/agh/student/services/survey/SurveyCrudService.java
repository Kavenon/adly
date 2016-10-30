package pl.edu.agh.student.services.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.student.exceptions.UnauthorizedAccessException;
import pl.edu.agh.student.model.survey.Survey;
import pl.edu.agh.student.repository.UserSurveyRepository;

import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class SurveyCrudService {

    private UserSurveyRepository repository;
    private OAuth2RestTemplate oAuth2RestTemplate;

    @Autowired
    public SurveyCrudService(UserSurveyRepository repository, OAuth2RestTemplate oAuth2RestTemplate) {
        this.repository = repository;
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    private Long getUserId() {
        return oAuth2RestTemplate.getForObject("http://localhost:9191/uaa/userId", Long.class);
    }

    public Survey add(Survey userSurvey) {
        userSurvey.setId(null);
        userSurvey.setUserId(getUserId());
        return repository.save(userSurvey);
    }

    public Survey update(Survey updatedItem, Integer id) {

        Survey one = repository.findOne(updatedItem.getId());

        if(!Objects.equals(getUserId(), one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        updatedItem.setId(id);
        updatedItem.setUserId(getUserId());
        return repository.save(updatedItem);

    }

    public void delete(Integer id) {

        Survey one = repository.findOne(id);

        if(!Objects.equals(getUserId(), one.getUserId())){
            throw new UnauthorizedAccessException();
        }

        one.setDeleted(true);
        repository.save(one);

    }

    public Survey get(Integer id) {
        return repository.findByIdAndUserId(id, getUserId());
    }

    public List<Survey> get() {
        return repository.findByUserIdAndDeleted(getUserId(), false);
    }



}
