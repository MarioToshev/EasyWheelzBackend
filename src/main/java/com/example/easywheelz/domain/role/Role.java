package com.example.easywheelz.domain.role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @NotBlank
    private Long id;
    @NotBlank
    private String roleName;
}
