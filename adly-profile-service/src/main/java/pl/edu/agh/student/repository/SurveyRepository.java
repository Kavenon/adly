package pl.edu.agh.student.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.Survey;

import java.util.List;

@Repository
public interface SurveyRepository extends PagingAndSortingRepository<Survey, Long> {

    List<Survey> findByUserId(Long userId);

}
