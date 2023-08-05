package com.example.traveltripapplication.model;

import android.text.TextUtils;
import android.util.Patterns;

public class RegisterModel {

    private String fullName;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;

    public RegisterModel() {}
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
    public boolean isConfirmPasswordValid() {
        return !TextUtils.isEmpty(confirmPassword) && confirmPassword.length() >= 6;
    }

    public boolean checkPassword() {
        return password.equals(confirmPassword);
    }

}
