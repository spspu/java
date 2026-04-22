package com.mainapp.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService{

    private final Cloudinary cloudinary;
    
    @Override
    public String uploadFile(byte[] fileBytes) throws Exception {
        Map uploadResult = cloudinary.uploader().upload(fileBytes, ObjectUtils.emptyMap());
        return uploadResult.get("secure_url").toString();
    }
    
    @Override
    public String getImageUrl(String name) {
        return cloudinary.url().secure(true).generate(name);
    }
    
    @Override
    public void deleteImage(String name) throws Exception {
        cloudinary.uploader().destroy(name, ObjectUtils.emptyMap());
    }
    
    
}