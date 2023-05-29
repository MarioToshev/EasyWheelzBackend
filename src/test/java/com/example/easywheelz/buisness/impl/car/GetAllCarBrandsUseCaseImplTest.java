package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.buisness.impl.statistics.BrandUsageStatisticsUseCaseImpl;
import com.example.easywheelz.buisness.interfaces.car.GetAllCarBrandsUseCase;
import com.example.easywheelz.persistance.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class GetAllCarBrandsUseCaseImplTest {

    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private GetAllCarBrandsUseCaseImpl service;

    @Test
    void testAllBrands(){

        List<String> brands = new ArrayList<>();
        brands.add("Ford");
        brands.add("Toyota");

        when(carRepository.findAllCarBrands()).thenReturn(brands);
        var result = service.getAllCarBrands();
        assertTrue(result.stream().count() == 2);
        verify(carRepository).findAllCarBrands();
    }
    @Test
    void testAllBrandsNoCars(){

        when(carRepository.findAllCarBrands()).thenReturn(new ArrayList<>());
        var result = service.getAllCarBrands();
        assertTrue(result.stream().count() == 0);
        verify(carRepository).findAllCarBrands();
    }

}