package com.marketplacex.auth.service;

import com.marketplacex.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AuthUserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username)
                .map(user -> new User(user.getUserName(), user.getPassword(),
                        List.of(new SimpleGrantedAuthority(user.getRole().name()))))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
