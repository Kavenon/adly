package pl.edu.agh.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.student.model.survey.Survey;
import pl.edu.agh.student.model.survey.client.survey.ClientSurvey;
import pl.edu.agh.student.model.survey.client.survey.ClientSurveyField;
import pl.edu.agh.student.repository.ProfilePropertyRepository;
import pl.edu.agh.student.repository.UserSurveyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SurveyClientService {

    private UserSurveyRepository surveyRepository;
    private ProfilePropertyRepository profilePropertyRepository;

    @Autowired
    public SurveyClientService(UserSurveyRepository surveyRepository, ProfilePropertyRepository profilePropertyRepository) {
        this.surveyRepository = surveyRepository;
        this.profilePropertyRepository = profilePropertyRepository;
    }

    public ClientSurvey getClientSurvey(Integer surveyId) {

        Survey one = surveyRepository.findOne(surveyId);
        if(one == null){
            throw new IllegalArgumentException("Survey does not exist");
        }

        return mapToClientSurvey(one);

    }

    private ClientSurvey mapToClientSurvey(Survey one) {
        List<ClientSurveyField> fieldList = one.getFieldList()
                .stream()
                .map(x -> {
                    ClientSurveyField field = new ClientSurveyField();
                    field.setFieldId(x.getId());
                    field.setFieldName(x.getName());
                    field.setFieldType(profilePropertyRepository.findOne(x.getProfilePropertyId()).getType());
                    return field;
                })
                .collect(Collectors.toList());

        ClientSurvey clientSurvey = new ClientSurvey();
        clientSurvey.setSurveyId(one.getId());
        clientSurvey.setFieldList(fieldList);
        return clientSurvey;
    }
}
