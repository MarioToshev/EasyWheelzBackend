package com.example.easywheelz.domain.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BrandCount {
    private String brand;
    private long count;
}
