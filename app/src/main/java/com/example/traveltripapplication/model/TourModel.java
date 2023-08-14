package com.example.traveltripapplication.model;

public class TourModel {
    private long tourID;
    private String tourCode;
    private String tourTitle;
    private int tourDuration;
    private String tourLocation;
    private int tourActive;
    private String Thumbnail;

    public TourModel() {
    }

    public TourModel(long tourID, String tourCode, String tourTitle, int tourDuration, String tourLocation, int tourActive, String thumbnail) {
        this.tourID = tourID;
        this.tourCode = tourCode;
        this.tourTitle = tourTitle;
        this.tourDuration = tourDuration;
        this.tourLocation = tourLocation;
        this.tourActive = tourActive;
        Thumbnail = thumbnail;
    }

    public long getTourID() {
        return tourID;
    }

    public void setTourID(long tourID) {
        this.tourID = tourID;
    }

    public String getTourCode() {
        return tourCode;
    }

    public void setTourCode(String tourCode) {
        this.tourCode = tourCode;
    }

    public String getTourTitle() {
        return tourTitle;
    }

    public void setTourTitle(String tourTitle) {
        this.tourTitle = tourTitle;
    }

    public int getTourDuration() {
        return tourDuration;
    }

    public void setTourDuration(int tourDuration) {
        this.tourDuration = tourDuration;
    }

    public String getTourLocation() {
        return tourLocation;
    }

    public void setTourLocation(String tourLocation) {
        this.tourLocation = tourLocation;
    }

    public int getTourActive() {
        return tourActive;
    }

    public void setTourActive(int tourActive) {
        this.tourActive = tourActive;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }
}
