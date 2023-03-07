package com.example.easywheelz.persistance.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class CarEntity {
    private long id;
    private  String licensePlate;
    private String model;
    private String brand;
    private double pricePerDay;
    private String color;
    private boolean availability;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarEntity carEntity)) {
            return false;
        }
        return id == carEntity.id &&
                Double.compare(carEntity.pricePerDay, pricePerDay) == 0 &&
                availability == carEntity.availability &&
                Objects.equals(licensePlate, carEntity.licensePlate) &&
                Objects.equals(model, carEntity.model) &&
                Objects.equals(brand, carEntity.brand) &&
                Objects.equals(color, carEntity.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licensePlate, model, brand, pricePerDay, color, availability);
    }

}
