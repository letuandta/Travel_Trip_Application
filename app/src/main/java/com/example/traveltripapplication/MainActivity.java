package com.example.traveltripapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.traveltripapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainBinding.getRoot());

        mActivityMainBinding.loginBtn.setOnClickListener(view -> {

        });

        mActivityMainBinding.registerBtn.setOnClickListener(view -> {

        });
    }
}