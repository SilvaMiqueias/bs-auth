package com.example.auth.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordDTO {
    private String username;
    private String oldPassword;
    private String password;
    private String confirmPassword;
}
