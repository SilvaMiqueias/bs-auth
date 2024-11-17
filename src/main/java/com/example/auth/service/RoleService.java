package com.example.auth.service;

import com.example.auth.model.Role;
import com.example.auth.model.enumerador.RoleName;
import com.example.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public Role findRole(RoleName name){
      return  repository.findByName(name).orElse(null);
    }
}
