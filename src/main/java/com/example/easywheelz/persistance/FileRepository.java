package com.example.easywheelz.persistance;

import org.springframework.web.multipart.MultipartFile;

public interface FileRepository {
    public String uploadPicture(MultipartFile photo);
}
