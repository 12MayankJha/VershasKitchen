package com.vershasKitchen.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.vershasKitchen.Helper.ImageHelper;
import com.vershasKitchen.entities.ImageDataBase;
import com.vershasKitchen.exceptions.FileStorageException;
import com.vershasKitchen.payload.ImageDataResponse;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private DataBaseService dbService;

	@Override
	public ImageDataBase storeImage(MultipartFile image, Boolean isPopular, String category, String subCategory,
			String name, String price) {
		// Normalize file name
		String imageName = StringUtils.cleanPath(image.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (imageName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + imageName);
			}

			ImageDataBase dbImage = new ImageDataBase(imageName, image.getContentType(), isPopular, category,
					subCategory, image.getBytes(), name, price);
			return dbService.save(dbImage);

		} catch (IOException ex) {
			throw new FileStorageException("Could not store image " + imageName + ". Please try again!", ex);
		}
	}

	@Override
	public ImageDataBase getImage(String imageId) {
		return dbService.findImageById(imageId);
	}

	@Override
	public Map<String, Map<String, List<ImageDataResponse>>> getAllImageData() {

		Map<String, Map<String, List<ImageDataResponse>>> categoryMap = new HashMap<String, Map<String, List<ImageDataResponse>>>();

		for (String category : ImageHelper.CATEGORIES) {
			List<ImageDataBase> imagefiles = dbService.findByCategory(category);
			Map<String, List<ImageDataResponse>> subCategoryMap = new HashMap<String, List<ImageDataResponse>>();
			Set<String> subCategories = new HashSet<>();
			for (ImageDataBase selectedImage : imagefiles) {
				if (selectedImage.getSubCategory() != null) {
					subCategories.add(selectedImage.getSubCategory());
				}
			}
			for (String subCategory : subCategories) {
				List<ImageDataResponse> imageList = new ArrayList<ImageDataResponse>();
				for (ImageDataBase selectedImage : imagefiles) {
					if (selectedImage.getSubCategory().equalsIgnoreCase(subCategory)) {
						String imageDownloadUri = "/downloadImage/" + selectedImage.getId();
						imageList.add(new ImageDataResponse(selectedImage.getId(), imageDownloadUri,
								selectedImage.getName(), selectedImage.getPrice()));
					}
				}
				subCategoryMap.put(subCategory, imageList);
			}

			categoryMap.put(category, subCategoryMap);
		}

		return categoryMap;
	}

	@Override
	public Map<String, Map<String, List<ImageDataResponse>>> getAllPopularImageData() {

		Map<String, Map<String, List<ImageDataResponse>>> categoryMap = new HashMap<String, Map<String, List<ImageDataResponse>>>();

		for (String category : ImageHelper.CATEGORIES) {
			List<ImageDataBase> imagefiles = dbService.findByCategory(category);
			Map<String, List<ImageDataResponse>> subCategoryMap = new HashMap<String, List<ImageDataResponse>>();
			Set<String> subCategories = new HashSet<>();
			for (ImageDataBase selectedImage : imagefiles) {
				if (selectedImage.getIsPopular()) {
					subCategories.add(selectedImage.getSubCategory());
				}
			}
			for (String subCategory : subCategories) {
				List<ImageDataResponse> imageList = new ArrayList<ImageDataResponse>();
				for (ImageDataBase selectedImage : imagefiles) {
					if (selectedImage.getSubCategory().equalsIgnoreCase(subCategory)) {
						if (selectedImage.getIsPopular()) {
						String imageDownloadUri = "/downloadImage/" + selectedImage.getId();
						imageList.add(new ImageDataResponse(selectedImage.getId(), imageDownloadUri,
								selectedImage.getName(), selectedImage.getPrice()));
						}
					}
				}
				subCategoryMap.put(subCategory, imageList);
			}
			categoryMap.put(category, subCategoryMap);
		}
		return categoryMap;
	}

}
