package com.example.easywheelz.buisness.impl.statistics;

import com.example.easywheelz.buisness.interfaces.statistics.IncomeStatisticsUseCase;
import com.example.easywheelz.domain.statistics.IncomePerMonth;
import com.example.easywheelz.persistance.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IncomeStatisticsUseCaseImpl implements IncomeStatisticsUseCase {
    private ReservationRepository repo;


    @Override
    public List<IncomePerMonth> getIncomePerMonthStatistics() {
        return repo.getIncomeOfReservationsPerMonth();
    }
}
