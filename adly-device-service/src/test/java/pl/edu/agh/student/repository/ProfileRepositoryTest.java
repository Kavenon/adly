package pl.edu.agh.student.repository;

import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.agh.student.model.Profile;

import java.util.Collections;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository repository;

    @Test
    public void test(){

        Profile profile = new Profile();
        profile.setUuid(UUID.randomUUID());
        profile.setDevices(Collections.singletonList(UUID.randomUUID()));


        repository.save(profile);


    }
}