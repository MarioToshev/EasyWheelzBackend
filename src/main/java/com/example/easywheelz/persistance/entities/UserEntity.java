package com.example.easywheelz.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class    UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank
    private String firstName;
    @Column
    @NotBlank
    private String lastName;
    @Column(unique = true)
    @NotBlank
    private String email;
    @Column(unique = true)
    @NotNull
    private long phone;
    @Column
    @NotNull
    private long driverLicense;
    @Column
    @NotNull
    private String password;
    @Column
    private Boolean disabled;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
