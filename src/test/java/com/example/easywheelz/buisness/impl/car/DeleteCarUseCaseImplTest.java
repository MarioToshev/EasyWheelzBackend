package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.customExeptions.InvalidCarCredentials;
import com.example.easywheelz.buisness.CarConverter;
import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class DeleteCarUseCaseImplTest {
    @Mock
    private CarRepository carRepository;
    @Mock
    private CarConverter carConverter;
    @InjectMocks
    private DeleteCarUseCaseImpl deleteCarUseCase;


    @Test
    void DeleteCarTest(){

        CarEntity carEnt = new CarEntity();
        carEnt.setId(1);


        when(carRepository.existsById(carEnt.getId())).thenReturn(true);
        when(carRepository.getReferenceById(carEnt.getId())).thenReturn(null);

        deleteCarUseCase.deleteCar(carEnt.getId());

        assertNull(carRepository.getReferenceById(carEnt.getId()));
        verify(carRepository).existsById(carEnt.getId());
        verify(carRepository).deleteById(carEnt.getId());
        

    }
    @Test
    void DeleteNotExistingCarTest(){
        CarEntity carEnt = new CarEntity();
        carEnt.setId(1);

        when(carRepository.existsById(carEnt.getId())).thenReturn(false);

        Exception exception = assertThrows(InvalidCarCredentials.class, () -> {
            deleteCarUseCase.deleteCar(carEnt.getId());
        });

        assertEquals("Car not found",exception.getMessage());
        verify(carRepository).existsById(carEnt.getId());
    }
}