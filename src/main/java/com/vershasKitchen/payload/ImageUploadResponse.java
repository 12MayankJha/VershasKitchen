package com.vershasKitchen.payload;

public class ImageUploadResponse {
    private String imageName;
    private String imagePath;
    private String fileType;
    private long size;
    private String id;

    public ImageUploadResponse(String fileName, String imagePath, String fileType, long size, String id) {
        this.imageName = fileName;
        this.imagePath = imagePath;
        this.fileType = fileType;
        this.size = size;
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
