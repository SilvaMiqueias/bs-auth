package com.example.auth.repository;

import com.example.auth.model.Role;
import com.example.auth.model.enumerador.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
