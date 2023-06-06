package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.buisness.interfaces.car.DeleteCarUseCase;
import com.example.easywheelz.custom.exeptions.InvalidCarCredentials;
import com.example.easywheelz.persistance.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCarUseCaseImpl implements DeleteCarUseCase {
    private final CarRepository carRepository;

    @Override
    public void deleteCar(long carId) {

        if (carRepository.existsById(carId)) {

            carRepository.deleteById(carId);

        } else {
            throw new InvalidCarCredentials("Car not found");
        }
    }
}
