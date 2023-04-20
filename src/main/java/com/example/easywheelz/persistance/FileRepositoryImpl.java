package com.example.easywheelz.persistance;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.easywheelz.persistance.FileRepository;
import com.example.easywheelz.customExeptions.ImageUploadExeption;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Repository
public class FileRepositoryImpl implements FileRepository {
    @Override
    public String uploadPicture(MultipartFile photo) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dxg6ys3z5");
        config.put("api_key", "796359929671874");
        config.put("api_secret", "dvowRp1R6OKApVoRr4libS6REQQ");
        Cloudinary cloudinary = new Cloudinary(config);

        try {
            var uploadResult = cloudinary.uploader().upload(photo.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("url").toString();
        } catch (IOException exception) {
            throw new ImageUploadExeption("There was an error uploading the picture");
        }
    }
}
