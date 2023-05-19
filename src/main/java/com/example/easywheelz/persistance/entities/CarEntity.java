package com.example.easywheelz.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;

@Entity
@Table(name = "cars")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @NotEmpty
    private  String licensePlate;
    @Column
    @NotEmpty
    private String model;
    @Column
    @NotEmpty
    private String brand;
    @Column
    @NotNull
    private double pricePerDay;
    @Column
    @NotEmpty
    private String color;
    @Column
    private String photoUrl;
    @Column
    private boolean availability;
}
