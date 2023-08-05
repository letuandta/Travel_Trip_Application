package com.example.traveltripapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.traveltripapplication.databinding.ActivityLoginBinding;
import com.example.traveltripapplication.interfaceviewmodel.LoginViewModelInterface;
import com.example.traveltripapplication.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements LoginViewModelInterface {

    private ActivityLoginBinding mActivityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginViewModel loginViewModel = new LoginViewModel();
        mActivityLoginBinding.setLoginViewModel(loginViewModel);
        mActivityLoginBinding.setLoginActivity(this);
    }

    @Override
    public void onClickLogin() {
        Log.d("CALLBACK", "get call back here");
        mActivityLoginBinding.message.setTextColor(Color.parseColor("#54ba81"));
    }
}