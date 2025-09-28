package com.prismcart.auth.service;

import com.prismcart.auth.dto.UserProfileResponse;
import org.springframework.security.core.Authentication;

public interface UserService {

    UserProfileResponse getMyProfile(Authentication authentication);
}
