package com.example.easywheelz.buisness.impl.car;

import com.example.easywheelz.buisness.interfaces.car.UploadCarPhotoUseCase;
import com.example.easywheelz.persistance.CarRepository;
import com.example.easywheelz.persistance.FileRepository;
import com.example.easywheelz.persistance.entities.CarEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UploadCarPhotoUseCaseImp implements UploadCarPhotoUseCase {
    private CarRepository carRepository;
    private FileRepository fileRepository;

    @Override
    public void uploadPicture(MultipartFile photo, long id) {
        Optional<CarEntity> car = carRepository.findById(id);
        if (car.isEmpty())
            throw new NoSuchElementException("Car not found");
        else {
            String url = fileRepository.uploadPicture(photo);
            CarEntity carEnt = car.get();
            carEnt.setPhotoUrl(url);
            carRepository.save(carEnt);
        }
    }
}
