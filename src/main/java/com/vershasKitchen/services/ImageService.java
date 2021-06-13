package com.vershasKitchen.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.vershasKitchen.entities.ImageDataBase;
import com.vershasKitchen.payload.ImageDataResponse;

public interface ImageService {

	ImageDataBase getImage(String fileId);

	ImageDataBase storeFile(MultipartFile file, Boolean isPopular, String category, String name, String price);
	
	Map<String, List<ImageDataResponse>> getAllImageData();

	Map<String, List<ImageDataResponse>> getAllPopularImageData();

	ResponseEntity<HttpStatus> deleteAll();
	
}
