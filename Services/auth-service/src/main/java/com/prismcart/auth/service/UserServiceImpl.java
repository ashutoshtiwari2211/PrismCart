package com.prismcart.auth.service;

import com.prismcart.auth.dto.UserProfileResponse;
import com.prismcart.auth.entity.User;
import com.prismcart.auth.exception.AuthException;
import com.prismcart.auth.repository.UserRepository;
import com.prismcart.security.model.AuthUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserProfileResponse getMyProfile(Authentication authentication) throws AuthException {
        AuthUserDetails authUser = (AuthUserDetails) authentication.getPrincipal(); // from JWT subject
        Optional<User> userOptional = userRepository.findByUserName(authUser.getUsername());
        if(userOptional.isEmpty()) {
            throw new AuthException("User not found");
        }
        User user = userOptional.get();
        return new UserProfileResponse(user.getId(), user.getUserName(), user.getEmail(), user.getRole().name());
    }
}
