package com.example.easywheelz.controller;

import com.example.easywheelz.buisness.interfaces.car.*;
import com.example.easywheelz.custom.exeptions.InvalidCarCredentials;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.car.CreateCarRequest;
import com.example.easywheelz.domain.car.CreateCarResponse;
import com.example.easywheelz.domain.car.UpdateCarRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    private CreateCarUseCase createCarUseCase;
    @Mock
    private UpdateCarUseCase updateCarUseCase;
    @Mock
    private DeleteCarUseCase deleteCarUseCase;
    @Mock
    private GetCarUseCase getCarsUseCase;
    @Mock
    private UploadCarPhotoUseCase uploadCarPhotoUseCase;
    @InjectMocks
    private CarController carController;

    @Test
     void createCarTest(){


        CreateCarRequest request =  CreateCarRequest.builder()
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();

        CreateCarResponse response = CreateCarResponse.builder()
                .id(1L)
                .build();
        when(createCarUseCase.createCar(request)).thenReturn(response);

        ResponseEntity<CreateCarResponse> createCarResponse = carController.createCar(request);

        assertEquals(HttpStatus.CREATED, createCarResponse.getStatusCode());
        assertEquals(1L, createCarResponse.getBody().getId());
        verify(createCarUseCase).createCar(request);
    }
    @Test
     void createCarTestWrongData(){

        CreateCarRequest request =  CreateCarRequest.builder()
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();


        when(createCarUseCase.createCar(request)).thenThrow(new InvalidCarCredentials(""));

        assertThrows(InvalidCarCredentials.class, () -> carController.createCar(request));
        verify(createCarUseCase).createCar(request);
    }
    @Test
    void updateCarTest() {
        UpdateCarRequest request = UpdateCarRequest.builder()
                .id(1L)
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();

        doNothing().when(updateCarUseCase).updateCar(request);
        ResponseEntity<Void> update = carController.updateCar(request);

        assertEquals(HttpStatus.NO_CONTENT, update.getStatusCode());
        verify(updateCarUseCase).updateCar(request);
    }
    @Test
    void updateCarWrongCredentialsTest() {
        UpdateCarRequest request = UpdateCarRequest.builder()
                .id(1L)
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();

        doThrow(new InvalidCarCredentials("")).when(updateCarUseCase).updateCar(request);

        assertThrows(InvalidCarCredentials.class, () -> carController.updateCar(request));
        verify(updateCarUseCase).updateCar(request);
    }

    @Test
    void deleteCarTest() {
        long carId = 1L;

        doNothing().when(deleteCarUseCase).deleteCar(carId);
        ResponseEntity<Void> delete = carController.deleteCar(carId);

        assertEquals(HttpStatus.NO_CONTENT, delete.getStatusCode());
        verify(deleteCarUseCase).deleteCar(carId);
    }
    @Test
    void deleteCarTestNotExisting() {
        long carID = 1L;

        doThrow(new InvalidCarCredentials("")).when(deleteCarUseCase).deleteCar(carID);

        assertThrows(InvalidCarCredentials.class, () -> carController.deleteCar(carID));
        verify(deleteCarUseCase).deleteCar(carID);
    }


    @Test
    void GetCarTest() {
        Car car = Car.builder()
                .id(1L)
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();

        when(getCarsUseCase.getCar(1L)).thenReturn(car);
        ResponseEntity<Car> get = carController.getCar(1L);


        assertEquals(HttpStatus.OK, get.getStatusCode());
        assertEquals(1L, get.getBody().getId());
        verify(getCarsUseCase).getCar(1L);
    }
    @Test
    void GetCarTestNotExistingCar() {
        when(getCarsUseCase.getCar(1L)).thenReturn(null);
        ResponseEntity<Car> get = carController.getCar(1L);

        assertEquals(HttpStatus.OK, get.getStatusCode());
        assertNull(get.getBody());
        verify(getCarsUseCase).getCar(1L);
    }

    @Test
    void GetAllCarsTest() {
        Car car = Car.builder()
                .id(1L)
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();
        Car car1 = Car.builder()
                .id(2L)
                .licensePlate("ABD123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("red")
                .availability(true)
                .build();

        ArrayList<Car> cars = new ArrayList<>();
        cars.add(car);
        cars.add(car1);

        when(getCarsUseCase.getAllCars()).thenReturn(cars);
        ResponseEntity<List<Car>> getAll = carController.getAllCars();


        assertEquals(HttpStatus.OK, getAll.getStatusCode());
        assertTrue(getAll.getBody().contains(car));
        assertTrue(getAll.getBody().contains(car1));
        verify(getCarsUseCase).getAllCars();
    }
    @Test
    void GetAllCarsTestEmpty() {

        ArrayList<Car> cars = new ArrayList<>();

        when(getCarsUseCase.getAllCars()).thenReturn(cars);
        ResponseEntity<List<Car>> getAll = carController.getAllCars();


        assertEquals(HttpStatus.OK, getAll.getStatusCode());
        assertEquals(0,getAll.getBody().size());
        verify(getCarsUseCase).getAllCars();
    }

    @Test
    void UploadPhoto() {

        String fileName = "car.png";
        String content = "content";
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        MockMultipartFile photo = new MockMultipartFile(fileName, fileName, "image/png", bytes);

        var response =  carController.uploadPhoto(1L,photo);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(uploadCarPhotoUseCase).uploadPicture(photo,1L);

    }
}