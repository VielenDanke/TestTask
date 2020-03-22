package kz.danke.test.task.service;

import kz.danke.test.task.model.User;

public interface UserService {

    User save(User user);

    User findByUsername(String username);

    User findByActivationCode(String activationCode);
}
