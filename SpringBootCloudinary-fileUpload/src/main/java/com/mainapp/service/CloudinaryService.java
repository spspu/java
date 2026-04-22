package com.mainapp.service;

public interface CloudinaryService {
	
	public String uploadFile(byte[] fileBytes) throws Exception;

    public String getImageUrl(String name);
    
    public void deleteImage(String name) throws Exception;

}
