package com.example.traveltripapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.traveltripapplication.admin.AdminActivity;
import com.example.traveltripapplication.customer.MainPageActivity;
import com.example.traveltripapplication.databinding.ActivityLoginBinding;
import com.example.traveltripapplication.viewmodel.interfaceListener.LoginViewModelInterface;
import com.example.traveltripapplication.model.UserModel;
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
    public void onClickLogin(UserModel userModel) {
        Log.d("CALLBACK", "get call back here");
        mActivityLoginBinding.message.setTextColor(Color.parseColor("#54ba81"));
        if (userModel.getIs_super_user() != 1) {
            Log.d("test", String.valueOf(userModel.getIs_super_user()));
            Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
            intent.putExtra("user", userModel);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
            intent.putExtra("user", userModel);
            startActivity(intent);
        }

    }
}