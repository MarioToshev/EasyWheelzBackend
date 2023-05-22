package com.example.easywheelz.persistance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class FileRepositoryImplTest {

    @Mock
    private FileRepositoryImpl repo;
    @Test
    void uploadPicture(){
        String fileName = "car.png";
        String content = "content";
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        MockMultipartFile photo = new MockMultipartFile(fileName, fileName, "image/png", bytes);

        when(repo.uploadPicture(photo)).thenReturn("url");

        var result = repo.uploadPicture(photo);

        assertNotNull(result);
    }
}