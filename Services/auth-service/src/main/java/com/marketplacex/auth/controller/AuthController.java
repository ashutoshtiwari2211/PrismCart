package com.marketplacex.auth.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/ping")
    public String ping() {
        return "Auth Service is working!";
    }
}
