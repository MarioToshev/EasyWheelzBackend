package com.example.easywheelz.domain.car;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterRequest {
        @Nullable
        private LocalDate startDate;
        @Nullable
        private LocalDate endDate;
        @Nullable
        private String brand;
        @Nullable
        private String sorting;
}
