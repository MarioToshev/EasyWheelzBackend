package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.buisness.converters.CarConverter;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.car.CreateCarRequest;
import com.example.easywheelz.domain.car.FilterRequest;
import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import org.aspectj.apache.bcel.classfile.LocalVariable;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilterCarUseCaseImplTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private CarConverter carConverter;
    @InjectMocks
    private FilterCarUseCaseImpl filterCarUseCase;

    @Test
   void filterCarsTest() {
        CarEntity carEnt1 =  CarEntity.builder()
                .licensePlate("DBC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(150.0)
                .color("Red")
                .build();

        CarEntity carEnt =  CarEntity.builder()
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .build();

        Car car1 =  Car.builder()
                .licensePlate("DBC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(150.0)
                .color("Red")
                .build();

        Car car =  Car.builder()
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .build();


        FilterRequest request =  FilterRequest.builder()
                                    .endDate(LocalDate.now())
                                    .startDate(LocalDate.now())
                                    .sorting("Ascending")
                                    .brand("Honda").build();

        when(carRepository.findAvailableCarsWithBrand(request.getStartDate(),request.getEndDate(), request.getBrand() ,Sort.by(Sort.Direction.DESC, "pricePerDay"))).thenReturn(new ArrayList<>(Arrays.asList(carEnt1,carEnt)));
        when(carConverter.convert(carEnt)).thenReturn(car);
        when(carConverter.convert(carEnt1)).thenReturn(car1);

        List<Car> filteredCars = filterCarUseCase.filterCars(request);
        assertTrue(filteredCars.get(0).getPricePerDay() > filteredCars.get(1).getPricePerDay());

    }
    @Test
    void filterCarsTestBrand() {
        CarEntity carEnt1 =  CarEntity.builder()
                .licensePlate("DBC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(150.0)
                .color("Red")
                .build();

        CarEntity carEnt =  CarEntity.builder()
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .build();

        Car car1 =  Car.builder()
                .licensePlate("DBC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(150.0)
                .color("Red")
                .build();

        Car car =  Car.builder()
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .build();


        FilterRequest request =  FilterRequest.builder()
                .endDate(LocalDate.now())
                .startDate(LocalDate.now())
                .brand("Honda").build();

        when(carRepository.findAvailableCarsWithBrand(request.getStartDate(),request.getEndDate(), request.getBrand() ,Sort.unsorted())).thenReturn(new ArrayList<>(Arrays.asList(carEnt1,carEnt)));
        when(carConverter.convert(carEnt)).thenReturn(car);
        when(carConverter.convert(carEnt1)).thenReturn(car1);

        List<Car> filteredCars = filterCarUseCase.filterCars(request);
        assertTrue(filteredCars.get(0).getBrand().equals( filteredCars.get(1).getBrand()));

    }

}