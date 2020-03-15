package kz.danke.test.task.service;

import kz.danke.test.task.model.User;
import kz.danke.test.task.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static kz.danke.test.task.utils.UserPopulate.USER_LIST;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Test
    public void setFieldShouldNotBeNull() {
        Assert.assertNotNull(userService);
    }

    @Test
    public void shouldSetCorrectPasswordAndSaveUserToDatabase() {
        Mockito.when(passwordEncoder.encode("TEST_1")).thenReturn("AFTER_TEST_1");
        Mockito.when(userRepository.save(USER_LIST.get(0))).thenReturn(USER_LIST.get(0));

        User afterSaving = userService.save(USER_LIST.get(0));

        Assert.assertEquals(USER_LIST.get(0).getUsername(), afterSaving.getUsername());
        Assert.assertEquals(afterSaving.getPassword(), "AFTER_TEST_1");

        Mockito.verify(userRepository, Mockito.times(1)).save(USER_LIST.get(0));
        Mockito.verify(passwordEncoder, Mockito.times(1)).encode("TEST_1");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldThrowExceptionWhenSearchingUserByName() {
        Mockito.when(userRepository.findByUsername("TEST_1")).thenReturn(Optional.empty());

        User userFromDatabase = userService.findByUsername("TEST_1");

        Assert.assertNull(userFromDatabase);

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername("TEST_1");
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
