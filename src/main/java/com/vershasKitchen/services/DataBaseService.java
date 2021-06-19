package com.vershasKitchen.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.vershasKitchen.entities.ImageDataBase;

public interface DataBaseService {

	ImageDataBase findImageById(String fileId);

	ResponseEntity<HttpStatus> deleteAll();

	List<ImageDataBase> findByCategory(String category);

	ImageDataBase save(ImageDataBase dbImage);

}
