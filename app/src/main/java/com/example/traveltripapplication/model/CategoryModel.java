package com.example.traveltripapplication.model;

public class CategoryModel {


    private long cateID;
    private String cateCode;
    private String cateName;
    private int drawableImage;
    public CategoryModel() {
    }

    public CategoryModel(long cateID, String cateCode, String cateName) {
        this.cateID = cateID;
        this.cateCode = cateCode;
        this.cateName = cateName;
    }

    public CategoryModel(long cateID, String cateCode, String cateName, int drawableImage) {
        this.cateID = cateID;
        this.cateCode = cateCode;
        this.cateName = cateName;
        this.drawableImage = drawableImage;
    }

    public int getDrawableImage() {
        return drawableImage;
    }

    public void setDrawableImage(int drawableImage) {
        this.drawableImage = drawableImage;
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
