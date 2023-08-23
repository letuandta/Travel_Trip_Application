package com.example.traveltripapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.traveltripapplication.data.database.DatabaseHelper;
import com.example.traveltripapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainBinding.getRoot());

        //region: create database
        DatabaseHelper.setContext(MainActivity.this);
        DatabaseHelper.initDB(); //mở comment lúc tạo database với các table và dữ liệu mẫu rồi nhớ đóng không thì bị lặp dữ liệu
        //Lở bị lỗi do quên đóng thì uninstall app trên máy ảo rồi build lại là được
        //end region

        mActivityMainBinding.loginBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        mActivityMainBinding.registerBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}