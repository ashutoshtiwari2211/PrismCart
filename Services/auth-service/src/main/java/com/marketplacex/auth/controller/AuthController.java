package com.marketplacex.auth.controller;

import com.marketplacex.auth.dto.LoginRequest;
import com.marketplacex.auth.dto.LoginResponse;
import com.marketplacex.auth.dto.RegisterRequest;
import com.marketplacex.auth.exception.AuthException;
import com.marketplacex.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        try {
            authService.register(request);
            return ResponseEntity.ok("User registered successfully!");
        } catch(AuthException authException) {
            return ResponseEntity.badRequest()
                    .body("Exception occurred while registering" + authException.getMessage());
        }
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        try {
            LoginResponse token = authService.login(request);
            return token;
        } catch (AuthException authException) {
            return new LoginResponse("IncorrectPassword");
        }
    }
}
