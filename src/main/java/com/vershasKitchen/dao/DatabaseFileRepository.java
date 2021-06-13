package com.vershasKitchen.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vershasKitchen.entities.ImageDataBase;

@Repository
public interface DatabaseFileRepository extends JpaRepository<ImageDataBase, String> {
	
//	@Query(value = "SELECT category FROM files where category IS NOT NULL", nativeQuery = true)
//	public List<String> getAllImageCategories();
	
	@Transactional
	public List<ImageDataBase> findByCategory(String category);
	

}

