package com.example.easywheelz.buisness.impl.statistics;

import com.example.easywheelz.persistance.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class IncomeStatisticsUseCaseImplTest {

    @Mock
    private ReservationRepository repo;
    @InjectMocks
    private  IncomeStatisticsUseCaseImpl service;
    @Test
    void testFilteringNoCars(){

        when(repo.getIncomeOfReservationsPerMonth()).thenReturn(new ArrayList<>());
        var result = service.getIncomePerMonthStatistics();
        assertTrue(result.stream().count() == 0);
        verify(repo).getIncomeOfReservationsPerMonth();
    }

}