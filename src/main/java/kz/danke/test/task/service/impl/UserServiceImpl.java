package kz.danke.test.task.service.impl;

import kz.danke.test.task.exceptions.WrongDateException;
import kz.danke.test.task.model.User;
import kz.danke.test.task.repository.UserRepository;
import kz.danke.test.task.service.UserService;
import kz.danke.test.task.util.DateValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderImpl mailSenderImpl;

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            String encode = passwordEncoder.encode(user.getPassword());
            user.setPassword(encode);
        }
        if (!DateValidation.isAgeValid(user.getDateOfBirth())) {
            throw new WrongDateException("Invalid date");
        }
        user.setActivationCode(UUID.randomUUID().toString());

        String message = String.format("Hello %s, \n" +
                        "Click this link to activate your account " + "http://localhost:8383/activate/%s",
                user.getUsername(),
                user.getActivationCode());

        mailSenderImpl.sendEmail(user.getEmail(), "Activation code", message);

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User findByActivationCode(String activationCode) {
        User user = userRepository.findByActivationCode(activationCode)
                .orElseThrow(() -> new WrongDateException("Activation code not found"));
        user.setActivationCode(null);
        return user;
    }
}
