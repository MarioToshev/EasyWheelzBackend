package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.FileRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class UploadCarPhotoUseCaseImpTest {
    @Mock
    private CarRepository carRepository;
    @Mock
    private FileRepository fileRepository;
    @InjectMocks
    private UploadCarPhotoUseCaseImp service;


    @Test
     void UploadPictureCorrectTest(){

        CarEntity car =  CarEntity.builder().id(1).build();

        String fileName = "car.png";
        String content = "content";
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        MockMultipartFile photo = new MockMultipartFile(fileName, fileName, "image/png", bytes);

        String url = "url";

        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));
        when(fileRepository.uploadPicture(photo)).thenReturn(url);

        service.uploadPicture(photo,car.getId());

        assertEquals("url", car.getPhotoUrl());
        verify(carRepository).findById(car.getId());
        verify(fileRepository).uploadPicture(photo);

    }
    @Test
     void UploadPictureInCorrectTest(){
        CarEntity car =  CarEntity.builder().id(1).build();

        String fileName = "car.png";
        String content = "content";
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        MockMultipartFile photo = new MockMultipartFile(fileName, fileName, "image/png", bytes);

        when(carRepository.findById(car.getId())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            service.uploadPicture(photo,car.getId());
        });
        assertEquals("Car not found",exception.getMessage());
    }
}