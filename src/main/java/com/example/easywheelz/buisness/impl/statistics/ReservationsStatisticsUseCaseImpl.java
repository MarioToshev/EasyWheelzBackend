package com.example.easywheelz.buisness.impl.statistics;

import com.example.easywheelz.buisness.interfaces.statistics.ReservationsStatisticsUseCase;
import com.example.easywheelz.domain.statistics.ReservationsPerMonth;
import com.example.easywheelz.persistance.ReservationRepository;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class ReservationsStatisticsUseCaseImpl implements ReservationsStatisticsUseCase {
    private ReservationRepository repo;
    @Override
    public List<ReservationsPerMonth> getReservationsPerMonthStatistics() {
       return repo.getAmountOfReservationsPerMonth();
    }
}
