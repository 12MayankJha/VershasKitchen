package com.vershasKitchen.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.vershasKitchen.Helper.ImageHelper;
import com.vershasKitchen.dao.DatabaseFileRepository;
import com.vershasKitchen.entities.ImageDataBase;
import com.vershasKitchen.exceptions.FileNotFoundException;
import com.vershasKitchen.exceptions.FileStorageException;
import com.vershasKitchen.payload.ImageDataResponse;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private DatabaseFileRepository dbFileRepository;

	@Override
	public ImageDataBase storeImage(MultipartFile image, Boolean isPopular, String category, String subCategory, String name, String price) {
		// Normalize file name
		String imageName = StringUtils.cleanPath(image.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (imageName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + imageName);
			}

			ImageDataBase dbImage = new ImageDataBase(imageName, image.getContentType(), isPopular, category,subCategory, image.getBytes(), name, price);
			return dbFileRepository.save(dbImage);
			
		} catch (IOException ex) {
			throw new FileStorageException("Could not store image " + imageName + ". Please try again!", ex);
		}
	}

	@Override
	public ImageDataBase getImage(String fileId) {
		return dbFileRepository.findById(fileId)
				.orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
	}
	
	@Override
	public Map<String,Map<String,List<ImageDataResponse>>> getAllImageData() {
		
		Map<String,List<ImageDataResponse>> subCategoryMap = new HashMap<String, List<ImageDataResponse>>();
		Map<String,List<ImageDataResponse>> categoryMap = new HashMap<String, List<ImageDataResponse>>();
		
		ImageHelper.db = dbFileRepository.findAll();
//		List<String> subCategories = dbFileRepository.findAllSubCategies();
//		System.out.println(subCategories);
		
		
		
		for(String category: ImageHelper.CATEGORIES) {
			List<ImageDataBase> imagefiles = dbFileRepository.findByCategory(category);
			List<ImageDataResponse> imageList = new  ArrayList<ImageDataResponse>();
			for(ImageDataBase selectedImage: imagefiles) {
				String imageDownloadUri = "/downloadImage/" + selectedImage.getId();
				imageList.add(new ImageDataResponse(selectedImage.getId(), imageDownloadUri, selectedImage.getName(), selectedImage.getPrice()));
			}
			
			subCategoryMap.put(category, imageList);
			
		}
		
		return subCategoryMap;
	}

	@Override
	public Map<String, List<ImageDataResponse>> getAllPopularImageData() {
		Map<String, List<ImageDataResponse>> imageMap = new HashMap<String, List<ImageDataResponse>>();

		for (String category : ImageHelper.CATEGORIES) {
			List<ImageDataBase> imagefile = dbFileRepository.findByCategory(category);
			List<ImageDataResponse> imageList = new ArrayList<ImageDataResponse>();
			for (ImageDataBase selectedImage : imagefile) {
				if (selectedImage.getIsPopular()) {
					String imageDownloadUri = "/downloadImage/" + selectedImage.getId();
					imageList.add(new ImageDataResponse(selectedImage.getId(), imageDownloadUri, selectedImage.getName(), selectedImage.getPrice()));
				}
			}

			imageMap.put(category, imageList);
		}

		return imageMap;
	}
	
	@Override
	public ResponseEntity<HttpStatus> deleteAll() {
		dbFileRepository.deleteAll();
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	

}
