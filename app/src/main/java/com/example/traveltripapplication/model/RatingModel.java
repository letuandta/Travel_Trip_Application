package com.example.traveltripapplication.model;

public class RatingModel {
    private long _id;
    private long tourId;
    private long userId;
    private double scores;
    private String message;
    private String createdDate;
    private UserModel userModel;

    public RatingModel() {
    }

    public RatingModel(long _id, long tourId, long userId, double scores, String message, String createdDate) {
        this._id = _id;
        this.tourId = tourId;
        this.userId = userId;
        this.scores = scores;
        this.message = message;
        this.createdDate = createdDate;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getTourId() {
        return tourId;
    }

    public void setTourId(long tourId) {
        this.tourId = tourId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getScores() {
        return scores;
    }

    public void setScores(double scores) {
        this.scores = scores;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

}
