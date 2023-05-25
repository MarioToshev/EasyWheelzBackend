package com.example.easywheelz.buisness.interfaces.statistics;

import com.example.easywheelz.domain.statistics.BrandCount;

import java.util.List;

public interface BrandUsageStatisticsUseCase {
    public List<BrandCount> brandUsageStatistics();
}
