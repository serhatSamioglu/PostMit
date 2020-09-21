package com.example.postmit.Classes;

public class Product {
    private String key;
    private String imageUrl;
    private String description;

    public Product(String key, String imageUrl, String description) {
        this.key = key;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public Product(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
