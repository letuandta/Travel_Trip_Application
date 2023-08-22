package com.example.traveltripapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.traveltripapplication.customer.fragment.CompleteUserInfoDialogFragment;
import com.example.traveltripapplication.databinding.ActivityRegisterBinding;
import com.example.traveltripapplication.viewmodel.interfaceListener.CompleteUserInfoInterface;
import com.example.traveltripapplication.viewmodel.interfaceListener.RegisterViewModelInterface;
import com.example.traveltripapplication.model.UserModel;
import com.example.traveltripapplication.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity implements RegisterViewModelInterface, CompleteUserInfoInterface{

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
    public void successRegister(UserModel user) {
        CompleteUserInfoDialogFragment completeUserInfoDialogFragment = CompleteUserInfoDialogFragment.newInstance(user, this);
        completeUserInfoDialogFragment.show(getSupportFragmentManager(), "complete_user_info");
    }

    @Override
    public void errorRegister() {

    }

    @Override
    public void saveSuccess() {
        Log.d("asgfasgas", "saveSuccess: ");
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void saveError() {
    }

    @Override
    public void saving() {
    }
}