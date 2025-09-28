package com.prismcart.auth.service;

import com.prismcart.auth.dto.LoginRequest;
import com.prismcart.auth.dto.LoginResponse;
import com.prismcart.auth.dto.RegisterRequest;
import com.prismcart.auth.entity.User;
import com.prismcart.auth.exception.AuthException;
import com.prismcart.auth.repository.UserRepository;
import com.prismcart.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(final UserRepository userRepository, final PasswordEncoder passwordEncoder,
                           final JwtUtil jwtUtil, final AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String register(final RegisterRequest request) throws AuthException {
        if (userRepository.findByUserName(request.getUserName()).isPresent()) {
            throw new AuthException("User already registered! Please login...");
        }

        User user = User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        User registeredUser = userRepository.save(user);
        return registeredUser.getId().toString();
    }

    @Override
    public LoginResponse login(final LoginRequest request) throws AuthException {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        String token;
        try{
         token = jwtUtil.generateToken(auth.getName(), auth.getAuthorities().iterator().next().getAuthority());
        }catch (Exception ex){
            throw new AuthException("Issue in generating token" + ex.getMessage());
        }
        return new LoginResponse(token);
    }
}
