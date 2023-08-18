package com.example.traveltripapplication.model;

public class SearchModel {
    private  String location;
    private  String toDay;
    private  String fromDay;
    private String numberPerson;

    public SearchModel() {
    }

    public SearchModel(String location, String toDay, String fromDay) {
        this.location = location;
        this.toDay = toDay;
        this.fromDay = fromDay;
    }

    public SearchModel(String location, String toDay, String fromDay, String numberPerson) {
        this.location = location;
        this.toDay = toDay;
        this.fromDay = fromDay;
        this.numberPerson = numberPerson;
    }

    public String getNumberPerson() {
        return numberPerson;
    }

    public void setNumberPerson(String numberPerson) {
        this.numberPerson = numberPerson;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getToDay() {
        return toDay;
    }

    public void setToDay(String toDay) {
        this.toDay = toDay;
    }

    public String getFromDay() {
        return fromDay;
    }

    public void setFromDay(String fromDay) {
        this.fromDay = fromDay;
    }
}
