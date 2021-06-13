package com.vershasKitchen.payload;

public class ImageDataResponse {

	private String id;
	private String imageURL;
	private String name;
	private String price;

	public ImageDataResponse(String id, String imageURL, String name, String price) {
		super();
		this.id = id;
		this.imageURL = imageURL;
		this.setName(name);
		this.setPrice(price);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
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
