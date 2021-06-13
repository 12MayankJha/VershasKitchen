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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ImageDataBase storeFile(MultipartFile file, Boolean isPopular, String category, String name, String price) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			ImageDataBase dbFile = new ImageDataBase(fileName, file.getContentType(), isPopular, category, file.getBytes(), name, price);
			return dbFileRepository.save(dbFile);
			
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	@Override
	public ImageDataBase getImage(String fileId) {
		return dbFileRepository.findById(fileId)
				.orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
	}
	
	@Override
	public Map<String,List<ImageDataResponse>> getAllImageData() {
		
		Map<String,List<ImageDataResponse>> imageMap = new HashMap<String, List<ImageDataResponse>>();
		
		for(String category: ImageHelper.CATEGORIES) {
			List<ImageDataBase> imagefile = dbFileRepository.findByCategory(category);
			List<ImageDataResponse> imageList = new  ArrayList<ImageDataResponse>();
			for(ImageDataBase selectedFile: imagefile) {
				String fileDownloadUri = "api/downloadImage/" + selectedFile.getId();
				imageList.add(new ImageDataResponse(selectedFile.getId(), fileDownloadUri, selectedFile.getName(), selectedFile.getPrice()));
			}
			
			imageMap.put(category, imageList);
		}
		
		return imageMap;
	}

	@Override
	public Map<String, List<ImageDataResponse>> getAllPopularImageData() {
		Map<String, List<ImageDataResponse>> imageMap = new HashMap<String, List<ImageDataResponse>>();

		for (String category : ImageHelper.CATEGORIES) {
			List<ImageDataBase> imagefile = dbFileRepository.findByCategory(category);
			List<ImageDataResponse> imageList = new ArrayList<ImageDataResponse>();
			for (ImageDataBase selectedFile : imagefile) {
				if (selectedFile.getIsPopular()) {
					String fileDownloadUri = "api/downloadImage/" + selectedFile.getId();
					imageList.add(new ImageDataResponse(selectedFile.getId(), fileDownloadUri, selectedFile.getName(), selectedFile.getPrice()));
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
