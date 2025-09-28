package com.prismcart.auth.controller;

import com.prismcart.auth.dto.UserProfileResponse;
import com.prismcart.auth.exception.AuthException;
import com.prismcart.auth.service.UserService;
import com.prismcart.commons.model.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserProfileResponse>>  getMyProfile(Authentication authentication) throws AuthException {
        UserProfileResponse user = userService.getMyProfile(authentication);
        return ResponseEntity.ok(ApiResponse.success(user, "User profile fetched successfully"));
    }
}
