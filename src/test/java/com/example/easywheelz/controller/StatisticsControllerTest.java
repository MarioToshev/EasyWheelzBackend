package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.statistics.BrandUsageStatisticsUseCase;
import com.example.easywheelz.buisness.interfaces.statistics.IncomeStatisticsUseCase;
import com.example.easywheelz.buisness.interfaces.statistics.ReservationsStatisticsUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsControllerTest {

    @Mock
    private BrandUsageStatisticsUseCase carStatisticsByBrand;
    @Mock
    private IncomeStatisticsUseCase incomeStatisticsUseCase;
    @Mock
    private ReservationsStatisticsUseCase reservationsStatisticsUseCase;
    @InjectMocks
    private StatisticsController statController;


    @Test
    void getCarStatisticsByBrandTest(){

        when(carStatisticsByBrand.brandUsageStatistics()).thenReturn(new ArrayList<>());
        var result = statController.getCarStatisticsByBrand();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(Collections.emptyList(), result.getBody());
        verify(carStatisticsByBrand).brandUsageStatistics();
    }

    @Test
    void getIncomeStatByMonthTest(){
        when(incomeStatisticsUseCase.getIncomePerMonthStatistics()).thenReturn(new ArrayList<>());
        var result = statController.getIncomeStatByMonth();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(Collections.emptyList(), result.getBody());
        verify(incomeStatisticsUseCase).getIncomePerMonthStatistics();

    }
    @Test
    void getReservationStatisticsTest(){

        when(reservationsStatisticsUseCase.getReservationsPerMonthStatistics()).thenReturn(new ArrayList<>());
        var result = statController.getReservationStatistics();
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(Collections.emptyList(), result.getBody());
        verify(reservationsStatisticsUseCase).getReservationsPerMonthStatistics();
    }



}