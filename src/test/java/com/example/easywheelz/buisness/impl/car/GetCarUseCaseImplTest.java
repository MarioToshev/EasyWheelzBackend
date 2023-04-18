package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.customExeptions.InvalidCarCredentials;
import com.example.easywheelz.buisness.CarConverter;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCarUseCaseImplTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private CarConverter carConverter;
    @InjectMocks
    private GetCarUseCaseImpl getCarUseCase;


    @Test
    void testGetUser() {
        CarEntity car =  CarEntity.builder()
                .id(1)
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();



        when(carRepository.existsById(car.getId())).thenReturn(true);
        when(carRepository.getReferenceById(car.getId())).thenReturn(car);

        Car car1 = getCarUseCase.getCar(car.getId());

        assertEquals(carConverter.convert(car),car1);
        verify(carRepository).existsById(car.getId());
        verify(carRepository).getReferenceById(car.getId());
    }

    @Test
    void testGetNonExistingCar() {

        Exception exception = assertThrows(InvalidCarCredentials.class, () -> {
            getCarUseCase.getCar(1L);
        });

        assertEquals("Car not found",exception.getMessage());
        verify(carRepository).existsById(1L);

    }
    @Test
    void testGetAllCarsNoCars() {
        assertEquals(Collections.emptyList(),getCarUseCase.getAllCars());
        verify(carRepository).findAll();
    }
}