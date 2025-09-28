package com.prismcart.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileResponse {
    @JsonIgnore
    private Long id;
    private String userName;
    private String email;
    private String role;
}
