package com.example.traveltripapplication.model;

public class UserModel {
    private long _ID;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private long state;
    private String last_name;
    private String first_name;
    private long contacts_id;
    private long is_super_user;
    private String created_date;
    private String birthday;

    public UserModel() {}


    public long get_ID() {
        return _ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLats_name(String lats_name) {
        this.last_name = lats_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public long getContacts_id() {
        return contacts_id;
    }

    public void setContacts_id(long contacts_id) {
        this.contacts_id = contacts_id;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public long getIs_super_user() {
        return is_super_user;
    }

    public void setIs_super_user(long is_super_user) {
        this.is_super_user = is_super_user;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
