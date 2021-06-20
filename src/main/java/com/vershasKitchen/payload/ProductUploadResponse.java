package com.vershasKitchen.payload;

public class ProductUploadResponse {
	private String id;
	private String productName;
	private String imagePath;

	public ProductUploadResponse(String id, String productName, String imagePath) {
		this.id = id;
		this.productName = productName;
		this.imagePath = imagePath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
