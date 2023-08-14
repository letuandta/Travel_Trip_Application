package com.example.traveltripapplication.model;

public class CategoryModel {


    private long cateID;
    private String cateCode;
    private String cateName;

    public CategoryModel(long cateID, String cateCode, String cateName) {
        this.cateID = cateID;
        this.cateCode = cateCode;
        this.cateName = cateName;
    }

    public long getCateID() {
        return cateID;
    }

    public void setCateID(long cateID) {
        this.cateID = cateID;
    }
    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String imageUrl) {
        this.cateCode = imageUrl;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
