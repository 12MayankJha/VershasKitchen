package com.vershasKitchen.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vershasKitchen.entities.ProductEntity;

@Repository
public interface DatabaseFileRepository extends JpaRepository<ProductEntity, String> {
	
//	@Query(value = "SELECT category FROM files where category IS NOT NULL", nativeQuery = true)
//	public List<String> getAllImageCategories();
	
	@Query(value = "SELECT subcategory FROM images where subcategory IS NOT NULL", nativeQuery = true)
	public List<String> findAllSubCategies(String category);
	
	public List<ProductEntity> findByCategory(String category);
	
//	@Transactional
//	public List<ImageDataBase> findBySubCategory(String subCategory);
	

}

