package com.example.easywheelz.buisness.interfaces.statistics;

import com.example.easywheelz.domain.statistics.ReservationsPerMonth;

import java.util.List;

public interface ReservationsStatisticsUseCase {
    public List<ReservationsPerMonth> getReservationsPerMonthStatistics();

}
