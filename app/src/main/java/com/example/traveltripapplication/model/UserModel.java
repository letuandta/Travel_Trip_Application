package com.example.traveltripapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserModel implements Parcelable {
    private long _ID;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private long state;
    private  String full_name;
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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeString(avatar);
        parcel.writeString(full_name);
        parcel.writeLong(_ID);
    }

    public UserModel(Parcel parcel){
        avatar = parcel.readString();
        full_name = parcel.readString();
        _ID = parcel.readLong();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel parcel) {
            return new UserModel(parcel);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
