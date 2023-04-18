package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.customExeptions.InvalidCarCredentials;
import com.example.easywheelz.buisness.converters.CarConverter;
import com.example.easywheelz.buisness.interfaces.car.CreateCarUseCase;
import com.example.easywheelz.domain.car.CreateCarRequest;
import com.example.easywheelz.domain.car.CreateCarResponse;
import com.example.easywheelz.persistance.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateCarUseCaseImpl implements CreateCarUseCase {

    private final CarRepository carRepository;
    private final CarConverter carConverter;

    @Override
    public CreateCarResponse createCar(CreateCarRequest request) {

        if(carRepository.existsByLicensePlate(request.getLicensePlate()))
        {
            throw new InvalidCarCredentials("A car with this licence plate already exists");
        }
        request.setAvailability(true);
        return CreateCarResponse.builder().id(
                carRepository.save(carConverter.convert(request)).getId()
        ).build();
    }
}
