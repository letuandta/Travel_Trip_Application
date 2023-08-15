package com.example.traveltripapplication.model;

public class TourItineraryModel {
    private long _id;
    private long tour_id;
    private int day;
    private String contentRaw;
    private String[] content;

    public TourItineraryModel() {
    }

    public TourItineraryModel(long _id, long tour_id, int day, String contentRaw) {
        this._id = _id;
        this.tour_id = tour_id;
        this.day = day;
        this.contentRaw = contentRaw;

        if(!contentRaw.isEmpty()){
            this.content = contentRaw.split("[|]");
        }
        else this.content = new String[]{};
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getTour_id() {
        return tour_id;
    }

    public void setTour_id(long tour_id) {
        this.tour_id = tour_id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getContentRaw() {
        return contentRaw;
    }

    public void setContentRaw(String contentRaw) {
        this.contentRaw = contentRaw;
    }

    public String[] getContent() {
        return content;
    }

    public void setContent(String[] content) {
        this.content = content;
    }
}
