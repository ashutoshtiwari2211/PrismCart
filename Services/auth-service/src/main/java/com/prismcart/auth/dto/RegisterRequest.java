package com.prismcart.auth.dto;

import com.prismcart.auth.entity.Role;
import lombok.*;
/**
 * DTO for user registration requests.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String userName;
    private String email;
    private String password;
    private Role role = Role.USER;
}
