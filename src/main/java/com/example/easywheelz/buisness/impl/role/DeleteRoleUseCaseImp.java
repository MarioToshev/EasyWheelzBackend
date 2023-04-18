package com.example.easywheelz.buisness.impl.role;

import com.example.easywheelz.customExeptions.InvalidRoleException;
import com.example.easywheelz.buisness.RoleConverter;
import com.example.easywheelz.buisness.interfaces.role.DeleteRoleUseCase;
import com.example.easywheelz.persistance.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteRoleUseCaseImp  implements DeleteRoleUseCase {
    private RoleRepository roleRepository;

    private RoleConverter converter;


    @Override
    public void deleteRole(long id) {
        if(!roleRepository.existsById(id)){
            throw  new InvalidRoleException("Role not found");
        }
        else {
            roleRepository.deleteById(id);
        }
    }
}
