package com.example.traveltripapplication.model;

public class PlaceFamousModel {
    private long categoryId;
    private String placeName;
    private String country;
    private String imageUrl;
    private Integer isFavorite;

    public PlaceFamousModel(long categoryId, String placeName, String country, String imageUrl, Integer isFavorite) {
        this.categoryId = categoryId;
        this.placeName = placeName;
        this.country = country;
        this.imageUrl = imageUrl;
        this.isFavorite = isFavorite;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Integer isFavorite) {
        this.isFavorite = isFavorite;
    }
}
