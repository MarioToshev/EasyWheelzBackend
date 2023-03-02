package com.fontys.easywheelz.persistance.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleEntity {
    private Long id;
    private String roleName;
}
