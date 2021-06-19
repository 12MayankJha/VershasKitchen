package com.vershasKitchen.services;

import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;
import com.vershasKitchen.entities.ImageDataBase;
import com.vershasKitchen.payload.ImageDataResponse;

public interface ImageService {

	Map<String, Map<String, List<ImageDataResponse>>> getAllImageData();

	Map<String, Map<String, List<ImageDataResponse>>> getAllPopularImageData();

	ImageDataBase storeImage(MultipartFile image, Boolean isPopular, String category, String subCategory, String name,
			String price);

	ImageDataBase getImage(String imageId);

}
