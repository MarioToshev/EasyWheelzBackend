package com.example.easywheelz.IntegrationTests;

import com.example.easywheelz.buisness.interfaces.car.*;
import com.example.easywheelz.domain.car.*;
import com.example.easywheelz.persistance.entities.CarEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
 class CarControllerIntegrationTests {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CreateCarUseCase createCarUseCase;
    @MockBean
    private UpdateCarUseCase updateCarUseCase;
    @MockBean
    private DeleteCarUseCase deleteCarUseCase;
    @MockBean
    private GetCarUseCase getCarUseCase;
    @MockBean
    private UploadCarPhotoUseCase uploadCarPhotoUseCase;
    @MockBean
    private FilterCarUseCase filterCarUseCase;
    @MockBean
    private GetAllCarBrandsUseCase getAllCarBrandsUseCase;


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllCarsTest() throws Exception {

        Car car = Car.builder().id(1L)
                .licensePlate("AB-12f4")
                .model("Chevrolet")
                .brand("Corvette")
                .pricePerDay(150)
                .color("Red")
                .build();
        Car car1 = Car.builder().id(1L)
                .licensePlate("AB-12f4")
                .model("Chevrolet")
                .brand("Corvette")
                .pricePerDay(150)
                .color("Red")
                .build();


        List<Car> cars = List.of(car, car1);

        when(getCarUseCase.getAllCars()).thenReturn(cars);


        mockMvc.perform(get("/cars").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
      [
            {
                "id": 1,
                "licensePlate": "AB-12f4",
                "model": "Chevrolet",
                "brand": "Corvette",
                "pricePerDay": 150.0,
                "color": "Red"
            },
             {
                "id": 1,
                "licensePlate": "AB-12f4",
                "model": "Chevrolet",
                "brand": "Corvette",
                "pricePerDay": 150.0,
                "color": "Red"
            }
        ]
"""));
        verify(getCarUseCase, times(1)).getAllCars();

    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllCarsReturnsEmptyTest() throws Exception {

        List<Car> cars = new ArrayList<>();

        when(getCarUseCase.getAllCars()).thenReturn(cars);


        mockMvc.perform(get("/cars").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                []
                """));
        verify(getCarUseCase, times(1)).getAllCars();
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getCarTest() throws Exception {

        Car car = Car.builder().id(1L)
                .licensePlate("AB-12f4")
                .model("Chevrolet")
                .brand("Corvette")
                .pricePerDay(150)
                .color("Red")
                .build();



        when(getCarUseCase.getCar(car.getId())).thenReturn(car);


        mockMvc.perform(get("/cars/" + car.getId() ).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
            {
                "id": 1,
                "licensePlate": "AB-12f4",
                "model": "Chevrolet",
                "brand": "Corvette",
                "pricePerDay": 150.0,
                "color": "Red"
            }
"""));
        verify(getCarUseCase, times(1)).getCar(car.getId());
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void CreateCarCarTest() throws Exception {

        CreateCarRequest carReq = CreateCarRequest.builder()
                .licensePlate("AB-12f4")
                .model("Chevrolet")
                .brand("Corvette")
                .pricePerDay(150)
                .color("Red")
                .build();

        when(createCarUseCase.createCar(carReq)).thenReturn(CreateCarResponse.builder().id(1L).build());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJSON = ow.writeValueAsString(carReq);


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE));

        verify(createCarUseCase, times(1)).createCar(carReq);
    }

    @Test
    @WithMockUser(username = "user", roles = {"Customer"})
    void CreateCarCarTestNotAdmin() throws Exception {

        CreateCarRequest carReq = CreateCarRequest.builder()
                .licensePlate("AB-12f4")
                .model("Chevrolet")
                .brand("Corvette")
                .pricePerDay(150)
                .color("Red")
                .build();

        when(createCarUseCase.createCar(carReq)).thenReturn(CreateCarResponse.builder().id(1L).build());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJSON = ow.writeValueAsString(carReq);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void UpdateCarTest() throws Exception {

        UpdateCarRequest carReq = UpdateCarRequest.builder()
                .id(1L)
                .licensePlate("AB-12f4")
                .model("Chevrolet")
                .brand("Corvette")
                .pricePerDay(150)
                .color("Red")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJSON = ow.writeValueAsString(carReq);


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andExpect(status().isNoContent());

        verify(updateCarUseCase, times(1)).updateCar(carReq);
    }
    @Test
    @WithMockUser(username = "customer", roles = {"Customer"})
    void UpdateCarNotAdminTest() throws Exception {

        UpdateCarRequest carReq = UpdateCarRequest.builder()
                .id(1L)
                .licensePlate("AB-12f4")
                .model("Chevrolet")
                .brand("Corvette")
                .pricePerDay(150)
                .color("Red")
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJSON = ow.writeValueAsString(carReq);


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void DeleteCarTest() throws Exception {
        var carId = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/cars/" + carId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(deleteCarUseCase, times(1)).deleteCar(carId);
    }
    @Test
    @WithMockUser(username = "customer", roles = {"Customer"})
    void DeleteCarNotAdminTest() throws Exception {

        var carId = 1L;

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/cars/" + carId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getFillteredCarsTest() throws Exception {

        Car car = Car.builder().id(1L)
                .licensePlate("AB-12f4")
                .model("Chevrolet")
                .brand("Corvette")
                .pricePerDay(150)
                .color("Red")
                .build();
        Car car1 = Car.builder().id(1L)
                .licensePlate("AB-12f4")
                .model("Chevrolet")
                .brand("Corvette")
                .pricePerDay(150)
                .color("Red")
                .build();

        FilterRequest request = FilterRequest.builder().brand("Chevrolet").build();


        List<Car> cars = List.of(car, car1);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJSON = ow.writeValueAsString(request);

        when(filterCarUseCase.filterCars(request)).thenReturn(cars);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cars/filters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJSON))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
      [
            {
                "id": 1,
                "licensePlate": "AB-12f4",
                "model": "Chevrolet",
                "brand": "Corvette",
                "pricePerDay": 150.0,
                "color": "Red"
            },
             {
                "id": 1,
                "licensePlate": "AB-12f4",
                "model": "Chevrolet",
                "brand": "Corvette",
                "pricePerDay": 150.0,
                "color": "Red"
            }
        ]
"""));
        verify(filterCarUseCase, times(1)).filterCars(request   );
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void getAllBrands() throws Exception {

        List<String> brands = List.of("mustang", "ferrari");


        when(getAllCarBrandsUseCase.getAllCarBrands()).thenReturn(brands);

        mockMvc.perform(get("/cars/brands").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                [
               "mustang",
                "ferrari"
                ]
                """));
        verify(getAllCarBrandsUseCase, times(1)).getAllCarBrands();
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void uploadPictureTest() throws Exception {

        List<String> brands = List.of("mustang", "ferrari");


        when(getAllCarBrandsUseCase.getAllCarBrands()).thenReturn(brands);

        mockMvc.perform(get("/cars/brands").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                [
               "mustang",
                "ferrari"
                ]
                """));
        verify(getAllCarBrandsUseCase, times(1)).getAllCarBrands();
    }

}
