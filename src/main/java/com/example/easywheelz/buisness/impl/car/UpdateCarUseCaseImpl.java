package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.buisness.converters.CarConverter;
import com.example.easywheelz.buisness.interfaces.car.UpdateCarUseCase;
import com.example.easywheelz.custom.exeptions.InvalidCarCredentials;
import com.example.easywheelz.domain.car.UpdateCarRequest;
import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateCarUseCaseImpl implements UpdateCarUseCase {
    private final CarRepository carRepository;
    private final CarConverter carConverter;

    @Override
    public void updateCar(UpdateCarRequest request) {
        Optional<CarEntity> carFromBase = carRepository.findById(request.getId());
        if (carFromBase.isPresent()){
            CarEntity carToUpdate = carConverter.convert(request);
            carToUpdate.setPhotoUrl(carFromBase.get().getPhotoUrl());
            carRepository.save(carToUpdate);
        }
        else {
            throw new InvalidCarCredentials("Car not found");
        }

    }
}
