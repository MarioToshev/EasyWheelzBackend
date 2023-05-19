package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.buisness.converters.CarConverter;
import com.example.easywheelz.buisness.interfaces.car.FilterCarUseCase;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.car.FilterRequest;
import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.CarSpecifications;
import com.example.easywheelz.persistance.entities.CarEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class FilterCarUseCaseImpl implements FilterCarUseCase {

    private CarRepository carRepo;
    private CarConverter converter;
    @Override
    public List<Car> filterCars(FilterRequest request) {
        Sort sort = Sort.unsorted();
        if (request.getSorting() != null) {
            if (request.getSorting().equals("ascending")) {
                sort = Sort.by(Sort.Direction.ASC, "pricePerDay");
            } else {
                sort = Sort.by(Sort.Direction.DESC, "pricePerDay");
            }
        }
        if (request.getEndDate() == null && request.getStartDate() == null && request.getBrand().isEmpty()){
            return carRepo.findAll(sort).stream().map(converter :: convert).toList();
        }
        else
        {
            return carRepo.findAvailableCarsWithBrand(request.getStartDate(),request.getEndDate(), request.getBrand(),sort)
                    .stream().map(converter :: convert).toList();
        }
    }
}
