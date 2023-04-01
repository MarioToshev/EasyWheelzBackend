package com.example.easywheelz.domain.role;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRoleRequest {
    @NotBlank
    private String roleName;
    @JsonCreator
    public CreateRoleRequest(@JsonProperty("roleName") String roleName){
    this.roleName = roleName;
    }
}
