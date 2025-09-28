package com.prismcart.auth.service;

import com.prismcart.auth.dto.UserProfileResponse;
import com.prismcart.auth.exception.AuthException;
import org.springframework.security.core.Authentication;

public interface UserService {

    UserProfileResponse getMyProfile(Authentication authentication) throws AuthException;
}
