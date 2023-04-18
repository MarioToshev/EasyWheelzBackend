package com.example.easywheelz.controller;

import com.example.easywheelz.Errors.IncorrectUserCredentialsError;
import com.example.easywheelz.Errors.InvalidCarCredentials;
import com.example.easywheelz.buisness.interfaces.car.CreateCarUseCase;
import com.example.easywheelz.buisness.interfaces.car.DeleteCarUseCase;
import com.example.easywheelz.buisness.interfaces.car.GetCarUseCase;
import com.example.easywheelz.buisness.interfaces.car.UpdateCarUseCase;
import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.car.CreateCarRequest;
import com.example.easywheelz.domain.car.CreateCarResponse;
import com.example.easywheelz.domain.car.UpdateCarRequest;
import com.example.easywheelz.domain.user.CreateUserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    @InjectMocks
    private CarController carController;

    @Test
    public void createCarTest(){


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

        ResponseEntity<CreateCarResponse> createUserResponse = carController.createCar(request);

        assertEquals(HttpStatus.CREATED, createUserResponse.getStatusCode());
        assertEquals(1L, createUserResponse.getBody().getId());
        verify(createCarUseCase).createCar(request);
    }
    @Test
    public void createUserTestWrongData(){

        CreateCarRequest request =  CreateCarRequest.builder()
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();

        CreateUserResponse response = CreateUserResponse.builder()
                .id(1L)
                .build();
        when(createCarUseCase.createCar(request)).thenThrow(new InvalidCarCredentials(""));

        assertThrows(InvalidCarCredentials.class, () -> carController.createCar(request));
        verify(createCarUseCase).createCar(request);
    }
    @Test
    void updateUserTest() {
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
    void updateUserWrongCredentialsTest() {
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
    void deleteUserTest() {
        long carId = 1L;

        doNothing().when(deleteCarUseCase).deleteCar(carId);
        ResponseEntity<Void> delete = carController.deleteCar(carId);

        assertEquals(HttpStatus.NO_CONTENT, delete.getStatusCode());
        verify(deleteCarUseCase).deleteCar(carId);
    }
    @Test
    void deleteUserTestNotExisting() {
        long carID = 1L;

        doThrow(new IncorrectUserCredentialsError("")).when(deleteCarUseCase).deleteCar(carID);

        assertThrows(IncorrectUserCredentialsError.class, () -> carController.deleteCar(carID));
        verify(deleteCarUseCase).deleteCar(carID);
    }


    @Test
    void GetUserTest() {
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
    void GetUserTestNotExistingUser() {
        when(getCarsUseCase.getCar(1L)).thenReturn(null);
        ResponseEntity<Car> get = carController.getCar(1L);

        assertEquals(HttpStatus.OK, get.getStatusCode());
        assertNull(get.getBody());
        verify(getCarsUseCase).getCar(1L);
    }

    @Test
    void GetAllUsersTest() {
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

        List<Car> cars = new ArrayList<Car>();
        cars.add(car);
        cars.add(car1);

        when(getCarsUseCase.getAllCars()).thenReturn(cars);
        ResponseEntity<List<Car>> getAll = carController.getAllCars();


        assertEquals(HttpStatus.OK, getAll.getStatusCode());
        assertEquals(cars.get(0).getId(), getAll.getBody().get(0).getId());
        assertEquals(cars.get(1).getId(), getAll.getBody().get(1).getId());
        verify(getCarsUseCase).getAllCars();
    }
}