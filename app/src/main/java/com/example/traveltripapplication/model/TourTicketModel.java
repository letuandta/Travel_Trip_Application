package com.example.traveltripapplication.model;

public class TourTicketModel {
    private long id;
    private long tour_id;
    private String title;
    private String description;
    private String tour_date;
    private int remaining;
    private int amount;
    private int active;
    private String price;

    public TourTicketModel() {
    }

    public TourTicketModel(long id, long tour_id, String title, String description, String tour_date, int remaining, int amount, int active, String price) {
        this.id = id;
        this.tour_id = tour_id;
        this.title = title;
        this.description = description;
        this.tour_date = tour_date;
        this.remaining = remaining;
        this.amount = amount;
        this.active = active;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTour_id() {
        return tour_id;
    }

    public void setTour_id(long tour_id) {
        this.tour_id = tour_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTour_date() {
        return tour_date;
    }

    public void setTour_date(String tour_date) {
        this.tour_date = tour_date;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
