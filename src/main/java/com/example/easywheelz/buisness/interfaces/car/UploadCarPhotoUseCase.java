package com.example.easywheelz.buisness.interfaces.car;

import org.springframework.web.multipart.MultipartFile;

public interface UploadCarPhotoUseCase {
    public void uploadPicture(MultipartFile photo, long id);
}
