package com.example.easywheelz.buisness.impl.statistics;

import com.example.easywheelz.buisness.interfaces.statistics.BrandUsageStatisticsUseCase;
import com.example.easywheelz.domain.statistics.BrandCount;
import com.example.easywheelz.persistance.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandUsageStatisticsUseCaseImpl implements BrandUsageStatisticsUseCase {

    private CarRepository carRepository;

    @Override
    public List<BrandCount> brandUsageStatistics() {
        return carRepository.getTheCountOfAllBrandsInReservations();
    }
}
