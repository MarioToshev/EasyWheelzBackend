package com.example.easywheelz.persistance.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleEntity {
    private Long id;
    private String roleName;
}
