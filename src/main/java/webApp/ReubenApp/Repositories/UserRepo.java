package webApp.ReubenApp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import webApp.ReubenApp.Dto.QuestionDto;
import webApp.ReubenApp.Entities.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
//    User findByname(String name);

    Optional<User> findByName(String name);
}
