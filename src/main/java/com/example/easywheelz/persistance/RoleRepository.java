package com.example.easywheelz.persistance;

import com.example.easywheelz.persistance.entities.RoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RoleRepository {
    boolean existsByRoleName(String roleName);
    RoleEntity findById(long roleId);
    RoleEntity save(RoleEntity role);
    RoleEntity delete(RoleEntity role);
    List<RoleEntity> findAll();
}

