package com.example.easywheelz.persistance;

import org.springframework.web.multipart.MultipartFile;

public interface FileRepository {
    String uploadPicture(MultipartFile photo);
}
