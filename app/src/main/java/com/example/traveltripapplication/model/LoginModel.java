package com.example.traveltripapplication.model;

import android.text.TextUtils;
import android.util.Patterns;


public class LoginModel {
    private String username;
    private String password;

    public LoginModel() {
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

    public boolean isEmailValid(){
        return !TextUtils.isEmpty(username) && Patterns.EMAIL_ADDRESS.matcher(username).matches();
    }

    public boolean isUserNameValid() {
        return !TextUtils.isEmpty(username);
    }

    public boolean isPasswordValid() {
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }
}
