package com.example.easywheelz.buisness.interfaces.car;

import com.example.easywheelz.domain.car.CreateCarRequest;
import com.example.easywheelz.domain.car.CreateCarResponse;

public interface CreateCarUseCase {
    CreateCarResponse createCar(CreateCarRequest request);

}
