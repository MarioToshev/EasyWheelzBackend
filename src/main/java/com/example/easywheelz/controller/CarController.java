package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.car.*;
import com.example.easywheelz.configuration.security.isauthenticated.IsAuthenticated;
import com.example.easywheelz.domain.car.*;
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
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CarController {
    private final CreateCarUseCase createCarUseCase;
    private final FindAllAvailableCarsUseCase findAllAvailableCarsUseCars;

    private final UpdateCarUseCase updateCarUseCase;
    private final DeleteCarUseCase deleteCarUseCase;
    private final GetCarUseCase getCarUseCase;
    private final UploadCarPhotoUseCase uploadCarPhotoUseCase;
    private final FilterCarUseCase filterCarUseCase;
    private final GetAllCarBrandsUseCase getAllCarBrandsUseCase;


    @PostMapping("")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<CreateCarResponse> createCar(@RequestBody CreateCarRequest request) {
        CreateCarResponse response = createCarUseCase.createCar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("")
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(getCarUseCase.getAllCars());
    }

//    @GetMapping("/filter")
//    public ResponseEntity<List<Car>> getAllAvailableCars(@RequestBody FilterCarsByAvailabilityRequest request) {
//        return ResponseEntity.ok(findAllAvailableCarsUseCars.GetAllAvailableCarsInDateRange(request));
//    }

    @GetMapping("/brands")
    public ResponseEntity<List<String>> getAllAvailableCars() {
        return ResponseEntity.ok(getAllCarBrandsUseCase.getAllCarBrands());
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> getCar(@PathVariable long carId) {
        return ResponseEntity.ok(getCarUseCase.getCar(carId));
    }

    @PutMapping("")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Void> updateCar(@RequestBody UpdateCarRequest request) {
        updateCarUseCase.updateCar(request);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable long carId) {
        deleteCarUseCase.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{carId}")
    @IsAuthenticated
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Void> uploadPhoto(@PathVariable(value = "carId") final long id,
                                            @RequestParam("photo") MultipartFile photo) {
        uploadCarPhotoUseCase.uploadPicture(photo, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/filters")
    public List<Car> filterCars(@RequestBody FilterRequest request) {
        return filterCarUseCase.filterCars(request);
    }

}
