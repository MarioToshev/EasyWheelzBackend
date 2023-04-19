package com.example.easywheelz.buisness.converters;

import com.example.easywheelz.domain.car.Car;
import com.example.easywheelz.domain.car.CreateCarRequest;
import com.example.easywheelz.domain.car.UpdateCarRequest;
import com.example.easywheelz.persistance.entities.CarEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
class CarConverterTest {


    private CarConverter converter;

    public CarConverterTest(){
        converter = new CarConverter();
    }

    @Test
    void convertCreateCarRequestToCarEntity() {

        CreateCarRequest car =  CreateCarRequest.builder()
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();

        CarEntity  carEnt = converter.convert(car);


        assertEquals(carEnt.getBrand(), car.getBrand());
        assertEquals(carEnt.getLicensePlate(), car.getLicensePlate());
        assertEquals(carEnt.getColor(), car.getColor());
        assertEquals(carEnt.getModel(), car.getModel());
        assertEquals(carEnt.isAvailability(), car.isAvailability());
        assertEquals(carEnt.getPricePerDay(), car.getPricePerDay());
    }
    @Test
    void convertCreateCarRequestToCarEntityWrong() {

        CreateCarRequest car =  null;

        assertThrows(NullPointerException.class, () -> converter.convert(car));
    }
    @Test
    void convertUpdateCarRequestToCarEntity() {

         UpdateCarRequest car  =  UpdateCarRequest.builder()
                 .id(1L)
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();

        CarEntity  carEnt = converter.convert(car);

        assertEquals(carEnt.getId(),car.getId());
        assertEquals(carEnt.getBrand(), car.getBrand());
        assertEquals(carEnt.getLicensePlate(), car.getLicensePlate());
        assertEquals(carEnt.getColor(), car.getColor());
        assertEquals(carEnt.getModel(), car.getModel());
        assertEquals(carEnt.isAvailability(), car.isAvailability());
        assertEquals(carEnt.getPricePerDay(), car.getPricePerDay());
    }
    @Test
    void convertUpdateCarRequestToCarEntityWrong() {

        UpdateCarRequest car =  null;

        assertThrows(NullPointerException.class, () -> converter.convert(car));
    }
    @Test
    void convertCarEntityToCar() {

        CarEntity carEntity  =  CarEntity.builder()
                .id(1L)
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();

        Car car = converter.convert(carEntity);

        assertEquals(car.getId(),carEntity.getId());
        assertEquals(car.getBrand(), carEntity.getBrand());
        assertEquals(car.getLicensePlate(), carEntity.getLicensePlate());
        assertEquals(car.getColor(), carEntity.getColor());
        assertEquals(car.getModel(), carEntity.getModel());
        assertEquals(car.isAvailability(), carEntity.isAvailability());
        assertEquals(car.getPricePerDay(), carEntity.getPricePerDay());
    }
    @Test
    void convertCarEntityToCarWrong() {

        CarEntity car =  null;

        assertThrows(NullPointerException.class, () -> converter.convert(car));
    }
    @Test
    void convertCarToCarEntity() {

        Car car =  Car.builder()
                .id(1L)
                .licensePlate("ABC123")
                .model("Civic")
                .brand("Honda")
                .pricePerDay(50.0)
                .color("Blue")
                .availability(true)
                .build();

        CarEntity carEntity = converter.convert(car);

        assertEquals(car.getId(),carEntity.getId());
        assertEquals(car.getBrand(), carEntity.getBrand());
        assertEquals(car.getLicensePlate(), carEntity.getLicensePlate());
        assertEquals(car.getColor(), carEntity.getColor());
        assertEquals(car.getModel(), carEntity.getModel());
        assertEquals(car.isAvailability(), carEntity.isAvailability());
        assertEquals(car.getPricePerDay(), carEntity.getPricePerDay());
    }
    @Test
    void convertCarToCarEntityWrong() {

        Car car =  null;

        assertThrows(NullPointerException.class, () -> converter.convert(car));
    }

}