package com.prismcart.auth.controller;

import com.prismcart.auth.dto.LoginRequest;
import com.prismcart.auth.dto.LoginResponse;
import com.prismcart.auth.dto.RegisterRequest;
import com.prismcart.auth.exception.AuthException;
import com.prismcart.auth.service.AuthService;
import com.prismcart.commons.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRequest request) throws AuthException {
            String userId = authService.register(request);
            return ResponseEntity.ok(ApiResponse.success("User {} registered successfully!", userId));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) throws AuthException {
        LoginResponse token = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success(token, "Login successful"));

    }
}
