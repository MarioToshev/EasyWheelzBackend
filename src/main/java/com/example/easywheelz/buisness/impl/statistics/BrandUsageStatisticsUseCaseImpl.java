package com.example.easywheelz.buisness.impl.statistics;

import com.example.easywheelz.buisness.interfaces.statistics.BrandUsageStatisticsUseCase;
import com.example.easywheelz.domain.statistics.BrandCount;
import com.example.easywheelz.persistance.CarRepository;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class BrandUsageStatisticsUseCaseImpl implements BrandUsageStatisticsUseCase {

    private CarRepository carRepository;
    @Override
    public List<BrandCount> brandUsageStatistics() {

        List<Tuple> queryRes = carRepository.getTheCountOfAllBrandsInReservations();

        List<BrandCount> result = new ArrayList<>();

        for (Tuple t: queryRes) {
            result.add(
                    BrandCount.builder()
                            .count(t.get("count", Long.class))
                            .brand(t.get("brand", String.class))
                            .build());
        }
        return result;
    }
}
