package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.car.CreateCarUseCase;
import com.example.easywheelz.buisness.interfaces.car.DeleteCarUseCase;
import com.example.easywheelz.buisness.interfaces.car.GetCarUseCase;
import com.example.easywheelz.buisness.interfaces.car.UpdateCarUseCase;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.car.CreateCarRequest;
import com.example.easywheelz.domain.car.CreateCarResponse;
import com.example.easywheelz.domain.car.UpdateCarRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cars")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CarController {
    private final CreateCarUseCase createCarUseCase;
    private final UpdateCarUseCase updateCarUseCase;
    private final DeleteCarUseCase deleteCarUseCase;
    private final GetCarUseCase getCarUseCase;

    @PostMapping("")
    public ResponseEntity<CreateCarResponse> createCar(@RequestBody CreateCarRequest request) {
        CreateCarResponse response = createCarUseCase.createCar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("")
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(getCarUseCase.getAllCars());
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> getCar(@PathVariable long carId) {
        return ResponseEntity.ok(getCarUseCase.getCar(carId));
    }
    @PutMapping("")
    public ResponseEntity<String> updateCar(@RequestBody UpdateCarRequest request) {
        updateCarUseCase.updateCar(request);
        return ResponseEntity.ok(String.valueOf(request.getId()));
    }
    @DeleteMapping("/{carId}")
    public ResponseEntity deleteCar(@PathVariable long carId) {
        deleteCarUseCase.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }

}
