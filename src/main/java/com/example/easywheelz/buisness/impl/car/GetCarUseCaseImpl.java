package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.buisness.CarConverter;
import com.example.easywheelz.buisness.interfaces.car.GetCarUseCase;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.persistance.CarRepository;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
        throw new RuntimeException("Car not found");
    }

    @Override
    public List<Car> getAllCars() {
        return  carRepository.findAll().stream().map(carConverter::convert).toList();
    }
}
