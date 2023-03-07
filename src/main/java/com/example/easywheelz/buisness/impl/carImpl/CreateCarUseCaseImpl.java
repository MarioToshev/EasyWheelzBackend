package com.example.easywheelz.buisness.impl.carImpl;

import com.example.easywheelz.buisness.CarConverter;
import com.example.easywheelz.buisness.carInterf.CreateCarUseCase;
import com.example.easywheelz.domain.car.CreateCarRequest;
import com.example.easywheelz.domain.car.CreateCarResponse;
import com.example.easywheelz.domain.user.CreateUserResponse;
import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.impl.FakeCarRepositoryImpl;
import com.example.easywheelz.persistance.impl.FakeRoleRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCarUseCaseImpl implements CreateCarUseCase {

    private final CarRepository carRepository;
    private final CarConverter carConverter;

    @Override
    public CreateCarResponse createCar(CreateCarRequest request) {
        return CreateCarResponse.builder().id(
                carRepository.save(carConverter.convert(request)).getId()
        ).build();
    }
}
