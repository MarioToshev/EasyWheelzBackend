package com.example.easywheelz.buisness.impl;

import com.example.easywheelz.buisness.RoleConverter;
import com.example.easywheelz.buisness.interfaces.role.CreateRoleUseCase;
import com.example.easywheelz.controller.RoleController;
import com.example.easywheelz.domain.role.CreateRoleRequest;
import com.example.easywheelz.domain.role.CreateRoleResponse;
import com.example.easywheelz.persistance.RoleRepository;
import com.example.easywheelz.persistance.entities.RoleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CreateRoleUseCaseImpl implements CreateRoleUseCase {

    private RoleRepository roleRepository;

    @Override
    @Transactional
    public CreateRoleResponse createRole(CreateRoleRequest request) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(request, roleEntity);

        roleEntity = roleRepository.save(roleEntity);

        return CreateRoleResponse.builder().id(
                roleEntity.getId()
        ).build();
    }


}
