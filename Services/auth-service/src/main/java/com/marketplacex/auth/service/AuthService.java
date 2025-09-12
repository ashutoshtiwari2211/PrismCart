package com.marketplacex.auth.service;

import com.marketplacex.auth.dto.LoginRequest;
import com.marketplacex.auth.dto.LoginResponse;
import com.marketplacex.auth.dto.RegisterRequest;
import com.marketplacex.auth.exception.AuthException;

public interface AuthService {

    String register(RegisterRequest request) throws AuthException;

    LoginResponse login(LoginRequest request) throws AuthException;
}
