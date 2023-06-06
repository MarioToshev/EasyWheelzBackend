package com.example.easywheelz.buisness.impl.role;

import com.example.easywheelz.buisness.converters.RoleConverter;
import com.example.easywheelz.buisness.interfaces.role.DeleteRoleUseCase;
import com.example.easywheelz.custom.exeptions.InvalidRoleException;
import com.example.easywheelz.persistance.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteRoleUseCaseImp implements DeleteRoleUseCase {
    private RoleRepository roleRepository;

    private RoleConverter converter;


    @Override
    public void deleteRole(long id) {
        if (!roleRepository.existsById(id)) {
            throw new InvalidRoleException("Role not found");
        } else {
            roleRepository.deleteById(id);
        }
    }
}
