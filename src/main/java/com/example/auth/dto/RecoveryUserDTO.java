package com.example.auth.dto;

import com.example.auth.model.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecoveryUserDTO {
    private Long id;
    private String email;
    private List<Role> roles;
}
