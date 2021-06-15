package com.vershasKitchen.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.vershasKitchen.entities.ImageDataBase;
import com.vershasKitchen.payload.ImageDataResponse;

public interface ImageService {

	ImageDataBase getImage(String imageId);

	ImageDataBase storeImage(MultipartFile image, Boolean isPopular, String category, String subCategory, String name, String price);
	
	Map<String, List<ImageDataResponse>> getAllImageData();

	Map<String, List<ImageDataResponse>> getAllPopularImageData();

	ResponseEntity<HttpStatus> deleteAll();
	
}
