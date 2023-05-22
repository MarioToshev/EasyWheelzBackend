
package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.buisness.converters.CarConverter;
import com.example.easywheelz.domain.car.CreateCarRequest;
import com.example.easywheelz.domain.car.CreateCarResponse;
import com.example.easywheelz.custom.exeptions.InvalidCarCredentials;
import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCarUseCaseImplTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private CarConverter carConverter;
    @InjectMocks
    private CreateCarUseCaseImpl createRoleUseCase;


    @Test
    void CreateCarTest(){


        CreateCarRequest car =  CreateCarRequest.builder()
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();

        CarEntity carEnt = new CarEntity();
        carEnt.setId(1);


        when(carRepository.existsByLicensePlate(car.getLicensePlate())).thenReturn(false);
        when(carRepository.save(carConverter.convert(car))).thenReturn(CarEntity.builder().id(carEnt.getId()).build());

       CreateCarResponse response =  createRoleUseCase.createCar(car);

        assertEquals(carEnt.getId(), response.getId());
        verify(carRepository).existsByLicensePlate(car.getLicensePlate());
        verify(carRepository).save(carConverter.convert(car));

    }

    @Test
    void CreateCarTestExistingUser(){
        CreateCarRequest car =  CreateCarRequest.builder()
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();

        CarEntity carEnt = new CarEntity();
        carEnt.setId(1);


        when(carRepository.existsByLicensePlate(car.getLicensePlate())).thenReturn(true);


        Exception exception = assertThrows(InvalidCarCredentials.class, () -> {
            createRoleUseCase.createCar(car);
        });

        assertEquals("A car with this licence plate already exists", exception.getMessage());
        verify(carRepository).existsByLicensePlate(car.getLicensePlate());
    }
}