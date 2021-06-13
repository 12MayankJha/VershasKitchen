package com.vershasKitchen.payload;

public class ImageDataResponse {

	private String id;
	private String imagePath;
	private String name;
	private String price;

	public ImageDataResponse(String id, String imagePath, String name, String price) {
		super();
		this.id = id;
		this.imagePath = imagePath;
		this.setName(name);
		this.setPrice(price);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imageURL) {
		this.imagePath = imageURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
