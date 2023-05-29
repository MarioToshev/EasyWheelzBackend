package com.example.easywheelz.buisness.impl.statistics;

import com.example.easywheelz.persistance.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ReservationsStatisticsUseCaseImplTest {
    @Mock
    private ReservationRepository repo;
    @InjectMocks
    private  ReservationsStatisticsUseCaseImpl service;
    @Test
    void testFilteringNoCars(){

        when(repo.getAmountOfReservationsPerMonth()).thenReturn(new ArrayList<>());
        var result = service.getReservationsPerMonthStatistics();
        assertTrue(result.stream().count() == 0);
        verify(repo).getAmountOfReservationsPerMonth();
    }
}