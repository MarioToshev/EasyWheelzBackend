package com.example.easywheelz.domain.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Month;

@Data
@Builder
@AllArgsConstructor
public class IncomePerMonth {
    private String month;
    private double income;
}
