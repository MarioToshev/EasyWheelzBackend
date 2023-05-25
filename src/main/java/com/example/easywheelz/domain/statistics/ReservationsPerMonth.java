package com.example.easywheelz.domain.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReservationsPerMonth {
    private long reservations_count;
    private String month;
}
