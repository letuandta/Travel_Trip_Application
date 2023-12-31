package com.example.traveltripapplication.model;

import java.io.Serializable;

public class TourModel implements Serializable {
    private long tourID;
    private String tourCode;
    private String tourTitle;
    private int tourDuration;
    private String tourLocation;
    private int tourActive;
    private String thumbnail;
    private String experience;
    private String moreInfo;
    private double ratingTour;

    public TourModel() {
        this.ratingTour = 0;
    }

    public TourModel(long tourID, String tourCode, String tourTitle,int tourDuration, String tourLocation, String thumbnail,
                     String moreInfo)  {
        this.tourID = tourID;
        this.tourCode = tourCode;
        this.tourTitle = tourTitle;
        this.tourDuration = tourDuration;
        this.tourLocation = tourLocation;
        this.thumbnail = thumbnail;
        this.moreInfo = moreInfo;
    }

    public TourModel(long tourID, String tourCode, String tourTitle, int tourDuration, String tourLocation, int tourActive, String thumbnail, String experience, String moreInfo) {
        this.tourID = tourID;
        this.tourCode = tourCode;
        this.tourTitle = tourTitle;
        this.tourDuration = tourDuration;
        this.tourLocation = tourLocation;
        this.tourActive = tourActive;
        this.thumbnail = thumbnail;
        this.experience = experience;
        this.moreInfo = moreInfo;
    }

    public double getRatingTour() {
        return ratingTour;
    }

    public void setRatingTour(double ratingTour) {
            this.ratingTour = ratingTour;
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
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
}
