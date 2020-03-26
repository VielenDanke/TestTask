package kz.danke.test.task.utils;

import kz.danke.test.task.model.Role;
import kz.danke.test.task.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserPopulate {

    public static final List<User> USER_LIST = Arrays.asList(
            User.builder()
                    .id(null)
                    .username("TEST_1")
                    .password("TEST_1")
                    .number(0)
                    .authorities(Collections.singleton(Role.ROLE_USER))
                    .dateOfBirth("2000-11-11")
                    .email("test1@test.ru")
                    .build(),
            User.builder()
                    .id(null)
                    .username("TEST_2")
                    .password("TEST_2")
                    .number(0)
                    .authorities(Collections.singleton(Role.ROLE_USER))
                    .dateOfBirth("2000-11-11")
                    .email("test2@test.ru")
                    .build(),
            User.builder()
                    .id(null)
                    .username("TEST_3")
                    .password("TEST_3")
                    .number(0)
                    .authorities(Collections.singleton(Role.ROLE_USER))
                    .dateOfBirth("2000-11-11")
                    .email("test3@test.ru")
                    .build()
    );
}
