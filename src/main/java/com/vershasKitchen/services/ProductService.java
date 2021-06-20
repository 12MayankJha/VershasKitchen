package com.vershasKitchen.services;

import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;
import com.vershasKitchen.entities.ProductEntity;
import com.vershasKitchen.payload.ProductDetails;

public interface ProductService {

	Map<String, Map<String, List<ProductDetails>>> getAllProducts();

	Map<String, List<ProductDetails>> getAllPopularProducts();

	ProductEntity storeProduct( String name, String price, String imagePath, Boolean isPopular, String category, String subCategory);

}
