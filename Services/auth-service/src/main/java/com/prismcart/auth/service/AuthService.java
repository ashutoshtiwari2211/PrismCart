package com.prismcart.auth.service;

import com.prismcart.auth.dto.LoginRequest;
import com.prismcart.auth.dto.LoginResponse;
import com.prismcart.auth.dto.RegisterRequest;
import com.prismcart.auth.exception.AuthException;

public interface AuthService {

    String register(RegisterRequest request) throws AuthException;

    LoginResponse login(LoginRequest request) throws AuthException;
}
