package com.example.easywheelz.domain.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateRoleRequest {
    private Long id;
    private String roleName;
}
