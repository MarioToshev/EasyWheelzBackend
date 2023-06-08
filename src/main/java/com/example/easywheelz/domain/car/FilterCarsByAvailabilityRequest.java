package com.example.easywheelz.domain.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
public class FilterCarsByAvailabilityRequest {
    private LocalDate startDate;
    private LocalDate endDate;
}
