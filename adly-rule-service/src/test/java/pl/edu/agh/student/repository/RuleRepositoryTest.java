package pl.edu.agh.student.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.agh.student.model.rule.Rule;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleRepositoryTest {

    @Autowired
    private RuleRepository ruleRepository;
    @Test
    public void findByUserIdAndActiveAndEventType() throws Exception {

        List<Rule> byUserIdAndActiveAndEventType = ruleRepository.findByUserIdAndActiveAndEventType(1L, true, "[{\"type\":\".BeaconDiscoverEvent\"}]");
        System.out.println(byUserIdAndActiveAndEventType);
    }

}