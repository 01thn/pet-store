package com.cinemastore.authservice.security;

import com.cinemastore.authservice.entity.User;
import com.cinemastore.authservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserSecurityDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserSecurityDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> maybeUser = userRepository.findByUsername(username);
        if (maybeUser.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " doesn't exist");
        }
        User user = maybeUser.get();
        return UserSecurity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .isEnabled(user.getIsEnabled())
                .authorities(new ArrayList<>() {{
                    add(user.getRole());
                }})
                .build();
    }
}
