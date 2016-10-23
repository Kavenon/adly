package pl.edu.agh.student.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.student.model.PredefinedProperty;
import pl.edu.agh.student.repository.PredefinedPropertyRepository;

import java.util.List;

@Component
@Transactional
public class PredefinedPropertyService {

    private PredefinedPropertyRepository repository;

    @Autowired
    public PredefinedPropertyService(PredefinedPropertyRepository repository) {
        this.repository = repository;
    }

    public List<PredefinedProperty> getBeacons() {
        return (List<PredefinedProperty>) repository.findAll();
    }

}
