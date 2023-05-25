package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.statistics.BrandUsageStatisticsUseCase;
import com.example.easywheelz.buisness.interfaces.statistics.IncomeStatisticsUseCase;
import com.example.easywheelz.buisness.interfaces.statistics.ReservationsStatisticsUseCase;
import com.example.easywheelz.configuration.security.isauthenticated.IsAuthenticated;
import com.example.easywheelz.domain.statistics.BrandCount;
import com.example.easywheelz.domain.statistics.IncomePerMonth;
import com.example.easywheelz.domain.statistics.ReservationsPerMonth;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private BrandUsageStatisticsUseCase carStatisticsByBrand;
    private IncomeStatisticsUseCase incomeStatisticsUseCase;
    private ReservationsStatisticsUseCase reservationsStatisticsUseCase;


    @GetMapping("/brand")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List<BrandCount>> getCarStatisticsByBrand() {

        return ResponseEntity.ok(carStatisticsByBrand.brandUsageStatistics());
    }
    @GetMapping("/income")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List<IncomePerMonth>> getIncomeStatByMonth() {
        return ResponseEntity.ok(incomeStatisticsUseCase.gerIncomePerMonthStatistics());
    }

    @GetMapping("/reservations")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List<ReservationsPerMonth>> getReservationStatistics() {
        return ResponseEntity.ok(reservationsStatisticsUseCase.getReservationsPerMonthStatistics());
    }



}
