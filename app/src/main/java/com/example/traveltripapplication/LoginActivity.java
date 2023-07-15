package com.example.traveltripapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.traveltripapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        B = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(B.getRoot());


    }
}