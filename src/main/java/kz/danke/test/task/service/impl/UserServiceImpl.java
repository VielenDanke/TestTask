package kz.danke.test.task.service.impl;

import kz.danke.test.task.model.User;
import kz.danke.test.task.repository.UserRepository;
import kz.danke.test.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
