package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.customExeptions.InvalidCarCredentials;
import com.example.easywheelz.buisness.converters.CarConverter;
import com.example.easywheelz.buisness.interfaces.car.GetCarUseCase;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.persistance.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetCarUseCaseImpl implements GetCarUseCase {

    private final CarRepository carRepository;
    private final CarConverter carConverter;
    @Override
    public Car getCar(long id) {
        if (carRepository.existsById(id)){
            return carConverter.convert(carRepository.getReferenceById(id));
        }
        throw new InvalidCarCredentials("Car not found");
    }

    @Override
    public List<Car> getAllCars() {
        return  carRepository.findAll().stream().map(carConverter::convert).toList();
    }
}
