package kz.danke.test.task.service.impl;

import kz.danke.test.task.model.User;
import kz.danke.test.task.repository.UserRepository;
import kz.danke.test.task.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return CustomUserDetails.create(user);
    }
}
