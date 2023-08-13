package com.example.traveltripapplication.model;

public class CategoryModel {


    private long cateID;
    private String imageUrl;
    private String cateName;

    public CategoryModel(long cateID, String imageUrl, String cateName) {
        this.cateID = cateID;
        this.imageUrl = imageUrl;
        this.cateName = cateName;
    }

    public long getCateID() {
        return cateID;
    }

    public void setCateID(long cateID) {
        this.cateID = cateID;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
