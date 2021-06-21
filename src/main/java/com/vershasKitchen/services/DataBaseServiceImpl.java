package com.vershasKitchen.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.vershasKitchen.dao.DatabaseFileRepository;
import com.vershasKitchen.entities.ProductEntity;


@Service
public class DataBaseServiceImpl implements DataBaseService {
	
	@Autowired
	private DatabaseFileRepository dbFileRepository;
	
	@Autowired
	private CacheManager cacheManager;
	
	@Override
	public ProductEntity save(ProductEntity product) {
		evictAllCaches();
		return dbFileRepository.save(product);
	}
	
	@Override
	@CacheEvict(cacheNames = {"product"})
	public ResponseEntity<HttpStatus> deleteAll() {
		dbFileRepository.deleteAll();
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	@Override
	@Cacheable(cacheNames = "product", key = "#category")
	public List<ProductEntity> findByCategory(String category) {
		return dbFileRepository.findByCategory(category);
	}
	
	
	@Scheduled(cron = "${cashing.timeout}")
	public void evictAllcachesAtIntervals() {
	    evictAllCaches();
	}

	private void evictAllCaches() {
		cacheManager.getCacheNames().stream()
	      .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}
	
	@Override
	@Cacheable(cacheNames = "product", key = "#id")
	public Optional<ProductEntity> findById(String id) {
		return dbFileRepository.findById(id);
	}
	
	@Override
	@CacheEvict(cacheNames = {"product"})
	public void deleteById(String id) {
		dbFileRepository.deleteById(id);
	}
	
	

}
