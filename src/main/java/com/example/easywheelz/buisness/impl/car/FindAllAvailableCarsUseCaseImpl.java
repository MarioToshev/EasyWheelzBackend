package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.buisness.converters.CarConverter;
import com.example.easywheelz.buisness.interfaces.car.FindAllAvailableCarsUseCase;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.car.FilterCarsByAvailabilityRequest;
import com.example.easywheelz.persistance.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class FindAllAvailableCarsUseCaseImpl implements FindAllAvailableCarsUseCase {

     private   final CarRepository carRepository;
     private final CarConverter converter;
    @Override
    public List<Car> GetAllAvailableCarsInDateRange(FilterCarsByAvailabilityRequest request) {
        return carRepository.findAvailableCars(request.getStartDate(),request.getEndDate()).stream().map(converter :: convert).toList();
    }
}
