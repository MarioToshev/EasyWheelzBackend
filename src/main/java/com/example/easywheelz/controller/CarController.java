package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.car.*;
import com.example.easywheelz.configuration.security.isauthenticated.IsAuthenticated;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.car.CreateCarRequest;
import com.example.easywheelz.domain.car.CreateCarResponse;
import com.example.easywheelz.domain.car.UpdateCarRequest;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {
    private final CreateCarUseCase createCarUseCase;
    private final UpdateCarUseCase updateCarUseCase;
    private final DeleteCarUseCase deleteCarUseCase;
    private final GetCarUseCase getCarUseCase;
    private final UploadCarPhotoUseCase uploadCarPhotoUseCase;


    @PostMapping("")
    public ResponseEntity<CreateCarResponse> createCar(@RequestBody CreateCarRequest request) {
        CreateCarResponse response = createCarUseCase.createCar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN","ROLE_CUSTOMER"})
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(getCarUseCase.getAllCars());
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> getCar(@PathVariable long carId) {
        return ResponseEntity.ok(getCarUseCase.getCar(carId));
    }
    @PutMapping("")
    public ResponseEntity<Void> updateCar(@RequestBody UpdateCarRequest request) {
        updateCarUseCase.updateCar(request);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable long carId) {
        deleteCarUseCase.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{carId}")
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    public ResponseEntity<Void> uploadPhoto(@PathVariable(value = "carId") final long id,
                                            @RequestParam("photo") MultipartFile photo) {
            uploadCarPhotoUseCase.uploadPicture(photo, id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

}
