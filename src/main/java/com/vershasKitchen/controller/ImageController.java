package com.vershasKitchen.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vershasKitchen.Helper.ImageHelper;
import com.vershasKitchen.entities.ImageDataBase;
import com.vershasKitchen.payload.ImageDataResponse;
import com.vershasKitchen.payload.ImageUploadResponse;
import com.vershasKitchen.services.ImageService;

@RestController
public class ImageController {

	@Autowired
	private ImageService imageService;

	@PostMapping("api/uploadImage")
	public ImageUploadResponse uploadImage(@RequestParam("file") MultipartFile file,
			@RequestParam("isPopular") Boolean isPopular,
			@RequestParam("category") String category,
			@RequestParam("name") String name,
			@RequestParam("price") String price
			) {

		ImageDataBase fileName = imageService.storeFile(file, isPopular, category, name, price);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadImage/")
				.path(fileName.getId()).toUriString();

		return new ImageUploadResponse(fileName.getImageName(), fileDownloadUri, file.getContentType(), file.getSize(),
				fileName.getId());
	}


	@GetMapping("api/downloadImage/{imageId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String imageId, HttpServletRequest request) {
		// Load file as Resource
		ImageDataBase image = imageService.getImage(imageId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getImageType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getImageName() + "\"")
				.body(new ByteArrayResource(image.getImageData()));
	}

	@GetMapping("api/getAllImageData")
	public ResponseEntity<Map<String, List<ImageDataResponse>>> getAllImageData() {
		Map<String, List<ImageDataResponse>> list = imageService.getAllImageData();
		if (!list.isEmpty()) {
			return ResponseEntity.ok(list);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("api/getAllPopularImageData")
	public ResponseEntity<Map<String, List<ImageDataResponse>>> getAllPopularImageData() {
		Map<String, List<ImageDataResponse>> list = imageService.getAllPopularImageData();
		if (!list.isEmpty()) {
			return ResponseEntity.ok(list);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}