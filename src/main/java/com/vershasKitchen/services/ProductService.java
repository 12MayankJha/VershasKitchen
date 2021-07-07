package com.vershasKitchen.services;

import java.util.List;
import java.util.Map;
import com.vershasKitchen.entities.ProductEntity;
import com.vershasKitchen.payload.ProductDetails;

public interface ProductService {

	Map<String, Map<String, List<ProductDetails>>> getAllProducts();

	Map<String, List<ProductDetails>> getAllPopularProducts();

	ProductEntity storeProduct(ProductEntity product);

	ProductEntity updateProduct(ProductEntity product);

	void deleteProduct(String productId);

	Map<String, List<ProductDetails>> getProductByCategory();

}
