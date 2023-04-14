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

    private RoleConverter converter;

    @Override
    public CreateRoleResponse createRole(CreateRoleRequest request) {
        if(request == null){
            throw new RuntimeException("Failed to save role entity");
        }
        RoleEntity roleEntity = roleRepository.save(converter.convert(request));
        return CreateRoleResponse.builder().id(
                roleEntity.getId()
        ).build();
    }


}
