package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.customExeptions.InvalidCarCredentials;
import com.example.easywheelz.buisness.CarConverter;
import com.example.easywheelz.domain.car.UpdateCarRequest;
import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCarUseCaseImplTest {
    @Mock
    private CarRepository carRepository;
    @Mock
    private CarConverter carConverter;
    @InjectMocks
    private UpdateCarUseCaseImpl updateCarUseCase;


    @Test
    void UpdateCarTest() {

        CarEntity car =  CarEntity.builder()
                .id(1)
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();

        car.setColor("red");
        when(carRepository.existsById(car.getId())).thenReturn(true);

        updateCarUseCase.updateCar(UpdateCarRequest.builder()
                        .id(car.getId())
                        .availability(car.isAvailability())
                        .brand(car.getBrand())
                        .color(car.getColor())
                        .licensePlate(car.getLicensePlate())
                        .model(car.getModel())
                        .pricePerDay(car.getPricePerDay())
                .build());

        when(carRepository.getReferenceById(car.getId())).thenReturn(car);

        var updatedCar = carRepository.getReferenceById(car.getId());

        assertEquals(updatedCar, car);
        verify(carRepository).existsById(car.getId());
        verify(carRepository).getReferenceById(car.getId());



    }
    @Test
    void UpdateCarTestNotExisting() {

        CarEntity car =  CarEntity.builder()
                .id(1)
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();

        car.setColor("red");
        when(carRepository.existsById(car.getId())).thenReturn(false);

        UpdateCarRequest request = UpdateCarRequest.builder()
                .id(car.getId())
                .availability(car.isAvailability())
                .brand(car.getBrand())
                .color(car.getColor())
                .licensePlate(car.getLicensePlate())
                .model(car.getModel())
                .pricePerDay(car.getPricePerDay())
                .build();

        Exception exception = assertThrows(InvalidCarCredentials.class, () -> {
            updateCarUseCase.updateCar(request);
        });

        assertEquals("Car not found",exception.getMessage());

        verify(carRepository).existsById(car.getId());
    }
}