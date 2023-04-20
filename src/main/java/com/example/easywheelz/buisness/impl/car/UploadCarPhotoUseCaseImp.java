package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.buisness.interfaces.car.UploadCarPhotoUseCase;
import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.FileRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.PublicKey;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class UploadCarPhotoUseCaseImp implements UploadCarPhotoUseCase {


    public CarRepository carRepository;
    public FileRepository fileRepository;

    @Override
    public void uploadPicture(MultipartFile photo, long id) {

        if (!carRepository.existsById(id))
            throw new NoSuchElementException("Car not found");
        else {
            CarEntity car = carRepository.getReferenceById(id);
            String url = fileRepository.uploadPicture(photo);
            car.setPhotoUrl(url);
            carRepository.save(car);
        }
    }
}
