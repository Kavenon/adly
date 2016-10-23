package pl.edu.agh.student.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.agh.student.model.property.EnumeratedPropertyType;
import pl.edu.agh.student.model.property.PredefinedProperty;
import pl.edu.agh.student.model.property.type.GenderPropertyEnum;
import pl.edu.agh.student.repository.PredefinedPropertyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PredefinedPropertyServiceTest {

    @Autowired
    private PredefinedPropertyRepository repository;

    @Test
    public void test(){

        PredefinedProperty predefinedProperty = new PredefinedProperty();
        predefinedProperty.setName("to delete");
        predefinedProperty.setType(new EnumeratedPropertyType(GenderPropertyEnum.stringNames()));


        repository.save(predefinedProperty);
    }
}