package com.vershasKitchen.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.vershasKitchen.dao.DatabaseFileRepository;
import com.vershasKitchen.entities.ImageDataBase;
import com.vershasKitchen.exceptions.FileNotFoundException;


@Service
public class DataBaseServiceImpl implements DataBaseService {
	
	@Autowired
	private DatabaseFileRepository dbFileRepository;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Override
	public ImageDataBase save(ImageDataBase dbImage) {
		evictAllCaches();
		return dbFileRepository.save(dbImage);
	}

	@Override
	@Cacheable(cacheNames = "image", key = "#imageId")
	public ImageDataBase findImageById(String imageId) {
		return dbFileRepository.findById(imageId)
				.orElseThrow(() -> new FileNotFoundException("File not found with id " + imageId));
	}
	
	@Override
	@CacheEvict(cacheNames = {"imageData", "image"})
	public ResponseEntity<HttpStatus> deleteAll() {
		dbFileRepository.deleteAll();
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@Override
	@Cacheable(cacheNames = "imageData", key = "#category")
	public List<ImageDataBase> findByCategory(String category) {
		System.out.println("fetching Book From DB");
		return dbFileRepository.findByCategory(category);
	}
	
	
	@Scheduled(cron = "${cashing.timeout}")
	public void evictAllcachesAtIntervals() {
		System.out.println("inside schedular");
	    evictAllCaches();
	}

	private void evictAllCaches() {
		cacheManager.getCacheNames().stream()
	      .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}
	

}
