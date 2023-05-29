package com.example.easywheelz.buisness.impl.statistics;

import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import jakarta.persistence.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandUsageStatisticsUseCaseImplTest {

    @Mock
    private CarRepository carRepository;
    @InjectMocks
    private  BrandUsageStatisticsUseCaseImpl service;

    @Test
    void testFilteringNoCars(){

        when(carRepository.getTheCountOfAllBrandsInReservations()).thenReturn(new ArrayList<>());
        var result = service.brandUsageStatistics();
        assertTrue(result.stream().count() == 0);
        verify(carRepository).getTheCountOfAllBrandsInReservations();
    }

}