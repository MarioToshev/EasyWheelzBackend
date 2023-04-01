package com.example.easywheelz.buisness.carInterf;

import com.example.easywheelz.domain.car.CreateCarRequest;
import com.example.easywheelz.domain.car.CreateCarResponse;

public interface CreateCarUseCase {
    CreateCarResponse createCar(CreateCarRequest request);

}
