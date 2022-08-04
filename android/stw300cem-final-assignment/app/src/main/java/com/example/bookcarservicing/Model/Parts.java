package com.example.bookcarservicing.Model;

public class Parts {

    private String partsname;
    private String price;
    private String model;
    private String description;
    private int imageId;
    private String partsimg;

    public Parts(String partsname, String price, String model, String description, int imageId) {
        this.partsname = partsname;
        this.price = price;
        this.model = model;
        this.description = description;
        this.imageId = imageId;
    }

    public String getPartsname() {
        return partsname;
    }

    public void setPartsname(String partsname) {
        this.partsname = partsname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getPartsimg() {
        return partsimg;
    }
}
