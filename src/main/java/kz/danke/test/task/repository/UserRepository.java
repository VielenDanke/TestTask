package kz.danke.test.task.repository;

import kz.danke.test.task.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"messages", "authorities"})
    Optional<User> findByUsername(String username);

    @EntityGraph(attributePaths = {"messages", "authorities"})
    Optional<User> findByActivationCode(String activationCode);
}
