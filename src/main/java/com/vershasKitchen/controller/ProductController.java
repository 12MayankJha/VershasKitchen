package com.vershasKitchen.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.vershasKitchen.entities.ProductEntity;
import com.vershasKitchen.payload.ProductDetails;
import com.vershasKitchen.payload.ProductUploadResponse;
import com.vershasKitchen.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/uploadProduct")
	public ProductUploadResponse uploadProduct(@RequestBody ProductEntity product) {
		ProductEntity storedImage = productService.storeProduct(product);
		return new ProductUploadResponse(storedImage.getId(), storedImage.getName(), storedImage.getImagePath());
	}

	@PostMapping("/uploadProducts")
	public List<ProductUploadResponse> uploadProducts(@RequestBody List<ProductEntity> products) {
		List<ProductUploadResponse> list = new ArrayList<ProductUploadResponse>();
		for (ProductEntity product : products) {
			ProductEntity storedImage = productService.storeProduct(product);
			list.add(new ProductUploadResponse(storedImage.getId(), storedImage.getName(), storedImage.getImagePath()));
		}
		return list;
	}
	
	@PutMapping("/uploadProduct")
	public ProductUploadResponse updateProduct(@RequestBody ProductEntity product) {
		ProductEntity storedImage = productService.updateProduct(product);
		return new ProductUploadResponse(storedImage.getId(), storedImage.getName(), storedImage.getImagePath());
	}
	
	@PutMapping("/uploadProducts")
	public List<ProductUploadResponse> updateProducts(@RequestBody List<ProductEntity> products) {
		List<ProductUploadResponse> list = new ArrayList<ProductUploadResponse>();
		for (ProductEntity product : products) {
			ProductEntity storedImage = productService.updateProduct(product);
			list.add(new ProductUploadResponse(storedImage.getId(), storedImage.getName(), storedImage.getImagePath()));
		}
		return list;
	}
	

	@GetMapping("/getAllProducts")
	public ResponseEntity<Map<String, Map<String, List<ProductDetails>>>> getAllProducts() {
		Map<String, Map<String, List<ProductDetails>>> list = productService.getAllProducts();
		if (!list.isEmpty()) {
			return ResponseEntity.ok(list);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getAllPopularProducts")
	public ResponseEntity<Map<String, List<ProductDetails>>> getAllPopularProducts() {
		Map<String, List<ProductDetails>> list = productService.getAllPopularProducts();
		if (!list.isEmpty()) {
			return ResponseEntity.ok(list);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getProductByCategory")
	public ResponseEntity<Map<String, List<ProductDetails>>> getProductByCategory() {
		Map<String, List<ProductDetails>> list = productService.getProductByCategory();
		if (!list.isEmpty()) {
			return ResponseEntity.ok(list);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteProduct/{productId}")
	public String deleteProduct(@PathVariable String productId) {
		try {
			productService.deleteProduct(productId);
			return "Product Deleted Successfully";
		} catch (Exception e) {
			return e.getMessage();
		}

	}

}