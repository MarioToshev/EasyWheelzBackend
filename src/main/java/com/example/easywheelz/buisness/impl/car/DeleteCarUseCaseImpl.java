package com.example.easywheelz.buisness.impl.carImpl;

import com.example.easywheelz.buisness.carInterf.DeleteCarUseCase;
import com.example.easywheelz.persistance.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteCarUseCaseImpl implements DeleteCarUseCase{
    private final CarRepository carRepository;
    @Override
    public void deleteCar(long carId) {
        carRepository.delete(carId);
    }
}
