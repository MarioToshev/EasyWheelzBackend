package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.customExeptions.InvalidCarCredentials;
import com.example.easywheelz.buisness.converters.CarConverter;
import com.example.easywheelz.buisness.interfaces.car.UpdateCarUseCase;
import com.example.easywheelz.domain.car.UpdateCarRequest;
import com.example.easywheelz.persistance.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateCarUseCaseImpl implements UpdateCarUseCase {
    private final CarRepository carRepository;
    private final CarConverter carConverter;

    @Override
    public void updateCar(UpdateCarRequest request) {
        if (carRepository.existsById(request.getId())){
            carRepository.save(carConverter.convert(request));

        }
        else {
            throw new InvalidCarCredentials("Car not found");
        }

    }
}
