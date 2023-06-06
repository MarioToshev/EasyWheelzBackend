package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.buisness.interfaces.car.GetAllCarBrandsUseCase;
import com.example.easywheelz.persistance.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GetAllCarBrandsUseCaseImpl implements GetAllCarBrandsUseCase {

    private final CarRepository repo;

    @Override
    public List<String> getAllCarBrands() {
        return repo.findAllCarBrands();
    }
}
