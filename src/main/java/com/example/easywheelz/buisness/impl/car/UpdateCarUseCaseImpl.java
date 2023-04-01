package com.example.easywheelz.buisness.impl.carImpl;

import com.example.easywheelz.buisness.CarConverter;
import com.example.easywheelz.buisness.carInterf.UpdateCarUseCase;
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
        carRepository.update(carConverter.convert(request));

    }
}
