package com.fontys.easywheelz.persistance;

import com.fontys.easywheelz.persistance.entity.RoleEntity;

import java.util.List;

public interface RoleRepository {
    boolean existsById(long roleId);
    RoleEntity findById(long roleId);
    RoleEntity save(RoleEntity user);
    RoleEntity delete(RoleEntity user);
    List<RoleEntity> findAll();
}
