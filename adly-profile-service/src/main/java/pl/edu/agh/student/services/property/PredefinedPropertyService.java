package pl.edu.agh.student.services.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.student.model.property.ProfileProperty;
import pl.edu.agh.student.repository.ProfilePropertyRepository;

import java.util.List;

@Component
@Transactional
public class PredefinedPropertyService {

    private ProfilePropertyRepository repository;

    @Autowired
    public PredefinedPropertyService(ProfilePropertyRepository repository) {
        this.repository = repository;
    }

    public List<ProfileProperty> findAllPredefined() {
        return repository.findByUserIdIsNull();
    }
}
