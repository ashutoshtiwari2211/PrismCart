package com.marketplacex.auth.dto;

import com.marketplacex.auth.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String userName;
    private String email;
    private String password;
    private Role role = Role.USER;
}
