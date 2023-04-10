package com.example.easywheelz.persistance.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
@Entity
@Table(name = "reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotBlank
    private LocalDate pickUpDate;
    @Column
    @NotBlank
    private LocalDate returnDate;
    @Column
    @NotBlank
    private double rentalRate;
    @Column
    @NotBlank
    private double totalCost;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity customer;
   @NonNull
    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;
}
