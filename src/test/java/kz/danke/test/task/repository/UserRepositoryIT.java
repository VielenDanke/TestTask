package kz.danke.test.task.repository;

import kz.danke.test.task.model.Role;
import kz.danke.test.task.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static kz.danke.test.task.utils.UserPopulate.USER_LIST;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:/application-test.properties")
public class UserRepositoryIT {

    private UserRepository userRepository;

    @Before
    public void setup() {
        USER_LIST.forEach(userRepository::save);
    }

    @Test
    public void shouldFindUserByUsername() {
        Optional<User> optionalUser = userRepository.findByUsername("TEST_1");

        Assert.assertTrue(optionalUser.isPresent());
        Assert.assertTrue(optionalUser.get().getAuthorities().contains(Role.ROLE_USER));
    }

    @Test
    public void shouldFindAllUsersInDatabase() {
        List<User> users = userRepository.findAll();

        Assert.assertEquals(users.size(), USER_LIST.size());
        Assert.assertTrue(users.contains(USER_LIST.get(0)));
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
