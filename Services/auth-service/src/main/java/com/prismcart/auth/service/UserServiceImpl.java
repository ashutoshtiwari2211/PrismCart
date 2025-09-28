package com.prismcart.auth.service;

import com.prismcart.auth.dto.UserProfileResponse;
import com.prismcart.auth.entity.User;
import com.prismcart.auth.repository.UserRepository;
import com.prismcart.security.model.AuthUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserProfileResponse getMyProfile(Authentication authentication) {
        AuthUserDetails authUser = (AuthUserDetails)authentication.getPrincipal(); // from JWT subject
        User user = userRepository.findByUserName(authUser.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found: " + authUser));
        return new UserProfileResponse(user.getId(), user.getUserName(), user.getEmail(), user.getRole().name());
    }
}
