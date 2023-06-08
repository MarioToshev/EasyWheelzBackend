package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.buisness.converters.CarConverter;
import com.example.easywheelz.buisness.interfaces.car.GetCarUseCase;
import com.example.easywheelz.custom.exeptions.InvalidCarCredentials;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetCarUseCaseImpl implements GetCarUseCase {

    private final CarRepository carRepository;
    private final CarConverter carConverter;

    @Override
    public Car getCar(long id) {
        Optional<CarEntity> car = carRepository.findById(id);
        if (car.isPresent()) {
            return carConverter.convert(car.get());
        }
        throw new InvalidCarCredentials("Car not found");
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll().stream().map(carConverter::convert).toList();
    }
}
