package com.example.traveltripapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class OrderModel implements Serializable {
    public OrderModel() {
    }

    private long _id;
    private long tour_id;
    private String tour_title;
    private long user_id;
    private long order_state_id;
    private String order_date;
    private String address;
    private String customer_name;
    private String phone;
    private Map<Long, Integer> order_tickets;



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

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getOrder_state_id() {
        return order_state_id;
    }

    public void setOrder_state_id(long order_state_id) {
        this.order_state_id = order_state_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Map<Long, Integer> getOrder_tickets() {
        return order_tickets;
    }

    public void setOrder_tickets(Map<Long, Integer> order_tickets) {
        this.order_tickets = order_tickets;
    }

    public String getTour_title() {
        return tour_title;
    }

    public void setTour_title(String tour_title) {
        this.tour_title = tour_title;
    }
}
