package com.vershasKitchen.entities;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "images")
public class ImageDataBase {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String imageName;

	private String imageType;
	
	private Boolean isPopular;
	
	private String category;
	
	private String name;
	
	private String price;
	
	@Lob
	//@Type(type = "org.hibernate.type.ImageType")
	private byte[] imageData;

	public ImageDataBase() {
		super();
	}


	public ImageDataBase(String imageName, String imageType, Boolean isPopular, String category, byte[] imageData, String name,
			String price) {
		super();
		this.imageName = imageName;
		this.imageType = imageType;
		this.isPopular = isPopular;
		this.category = category;
		this.imageData = imageData;
		this.name = name;
		this.price = price;
	}


	public String getId() {
		return id;
	}

	public String getImageName() {
		return imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setImageName(String fileName) {
		this.imageName = fileName;
	}

	public void setImageType(String fileType) {
		this.imageType = fileType;
	}

	public void setImageData(byte[] data) {
		this.imageData = data;
	}


	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public Boolean getIsPopular() {
		return isPopular;
	}


	public void setIsPopular(Boolean isPopular) {
		this.isPopular = isPopular;
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
