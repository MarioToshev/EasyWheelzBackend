package com.example.easywheelz.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "cars")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotBlank
    private  String licensePlate;
    @Column
    @NotBlank
    private String model;
    @Column
    @NotBlank
    private String brand;
    @Column
    @NotBlank
    private double pricePerDay;
    @Column
    @NotBlank
    private String color;
    @Column
    @NotBlank
    private boolean availability;
}
