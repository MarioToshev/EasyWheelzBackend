package com.example.easywheelz.persistance;

import com.example.easywheelz.persistance.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    public RoleEntity findByRoleName(String roleName);

    public boolean existsRoleByRoleName(String roleName);
}

