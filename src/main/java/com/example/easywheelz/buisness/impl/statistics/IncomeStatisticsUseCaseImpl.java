package com.example.easywheelz.buisness.impl.statistics;

import com.example.easywheelz.buisness.interfaces.statistics.IncomeStatisticsUseCase;
import com.example.easywheelz.domain.reservation.Reservation;
import com.example.easywheelz.domain.statistics.IncomePerMonth;
import com.example.easywheelz.domain.statistics.ReservationsPerMonth;
import com.example.easywheelz.persistance.ReservationRepository;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
