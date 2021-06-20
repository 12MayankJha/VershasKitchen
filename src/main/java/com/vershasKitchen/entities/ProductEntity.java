package com.vershasKitchen.entities;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "products")
public class ProductEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private Boolean isPopular;

	private String category;

	private String subcategory;

	private String name;

	private String price;

	private String imagePath;

	public ProductEntity() {
		super();
	}

	public ProductEntity(String name, String price, String imagePath, Boolean isPopular, String category,
			String subCategory) {
		super();
		this.name = name;
		this.price = price;
		this.imagePath = imagePath;
		this.isPopular = isPopular;
		this.category = category;
		this.setSubcategory(subCategory);
	}

	public String getId() {
		return id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setImageData(String imagePath) {
		this.imagePath = imagePath;
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

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}



}
