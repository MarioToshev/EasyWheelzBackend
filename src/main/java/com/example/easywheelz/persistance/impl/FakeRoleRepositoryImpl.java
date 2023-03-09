package com.example.easywheelz.persistance.impl;

import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.entities.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Repository
public class FakeRoleRepositoryImpl implements RoleRepository {
    private final List<RoleEntity> roles;
    private static long NEXT_ID = 1;


    public FakeRoleRepositoryImpl() {

        roles = new ArrayList<>();
    }

    @Override
    public boolean existsByRoleName(String roleName) {
        return roles.stream()
                .anyMatch(roleEntity -> roleEntity.getRoleName().equals(roleName));
    }

    @Override
    public RoleEntity findById(long roleId) {
        return roles.stream()
                .filter(roleEntity -> roleEntity.getId().equals(roleId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public RoleEntity save(RoleEntity role) {
        role.setId(NEXT_ID);
        NEXT_ID++;
        roles.add(role);
        return role;
    }

    @Override
    public void delete(long roleId) {
        roles.remove(roles.stream().filter(userEntity -> userEntity.getId().equals(roleId)).findFirst().orElse(null));
    }

    @Override
    public List<RoleEntity> findAll() {
        return Collections.unmodifiableList(roles);
    }
}
