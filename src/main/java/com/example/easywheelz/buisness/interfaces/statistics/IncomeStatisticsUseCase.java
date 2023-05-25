package com.example.easywheelz.buisness.interfaces.statistics;

import com.example.easywheelz.domain.statistics.IncomePerMonth;

import java.util.List;

public interface IncomeStatisticsUseCase {
    public List<IncomePerMonth> gerIncomePerMonthStatistics();
}
