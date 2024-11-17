package com.example.auth.dto;

import com.example.auth.model.enumerador.RoleName;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String name;
    RoleName role;
}
