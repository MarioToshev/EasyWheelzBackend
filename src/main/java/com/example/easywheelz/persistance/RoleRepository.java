package com.example.easywheelz.persistance;

import com.example.easywheelz.persistance.entities.RoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleRepository  extends JpaRepository<RoleEntity, Long> {
    public RoleEntity findByRoleName(String roleName);
    public boolean existsRoleByRoleName(String roleName);
}

