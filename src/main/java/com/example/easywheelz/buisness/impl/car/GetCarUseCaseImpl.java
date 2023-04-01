package com.example.easywheelz.buisness.impl.carImpl;

import com.example.easywheelz.buisness.CarConverter;
import com.example.easywheelz.buisness.carInterf.GetCarUseCase;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.persistance.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetCarUseCaseImpl implements GetCarUseCase {

    private final CarRepository carRepository;
    private final CarConverter carConverter;
    @Override
    public Car getCar(long id) {
       return carConverter.convert(carRepository.findById(id));
    }

    @Override
    public List<Car> getAllCars() {
        return  carRepository.findAll().stream().map(carConverter::convert).toList();
    }
}
