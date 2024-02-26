package webApp.ReubenApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webApp.ReubenApp.Dto.QuestionDto;
import webApp.ReubenApp.Entities.Questions;

@Repository
public interface QuestionRepo extends JpaRepository<Questions, Long> {
}
