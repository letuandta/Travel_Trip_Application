package com.example.traveltripapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.traveltripapplication.databinding.ActivityRegisterBinding;
import com.example.traveltripapplication.interfaceviewmodel.RegisterViewModelInterface;
import com.example.traveltripapplication.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity implements RegisterViewModelInterface {

    private ActivityRegisterBinding mActivityRegisterBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        RegisterViewModel registerViewModel = new RegisterViewModel();
        mActivityRegisterBinding.setRegisterViewModel(registerViewModel);
        mActivityRegisterBinding.setRegisterlActivity(this);
    }

    @Override
    public void successRegister() {

    }

    @Override
    public void errorRegister() {

    }
}