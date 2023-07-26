package com.example.traveltripapplication.viewmodel;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.traveltripapplication.BR;
import com.example.traveltripapplication.presenter.AuthPresenter;
import com.example.traveltripapplication.enumapp.LoginEnum;
import com.example.traveltripapplication.interfaceviewmodel.LoginViewModelInterface;
import com.example.traveltripapplication.model.LoginModel;

import java.util.concurrent.CompletableFuture;

public class LoginViewModel extends BaseObservable {

    private LoginModel mLoginModel = new LoginModel();
    public ObservableField<String> message = new ObservableField<>();

    //Tao mot doi tuong user

    @Bindable
    public LoginModel getLoginModel() {
        return mLoginModel;
    }

    public void setLoginModel(LoginModel mLoginModel) {
        this.mLoginModel = mLoginModel;
        notifyPropertyChanged(BR.loginModel);
    }

    public void onClickLoginButton(LoginViewModelInterface loginViewModelInterface)  {
        if((getLoginModel().isEmailValid() || getLoginModel().isPhoneValid()) && getLoginModel().isPasswordValid()) {
            Log.d("LOGIN", getLoginModel().getUsername() + getLoginModel().getPassword());
            CompletableFuture<Boolean> checkLogin = AuthPresenter.Login(getLoginModel().getUsername(), getLoginModel().getPassword(),
                    getLoginModel().isEmailValid() ? LoginEnum.EMAIL : LoginEnum.PHONE);
            checkLogin.thenAccept(result -> {
                if(result){
                    message.set("Login success !!");
                    //Gan gia tri cho user
                }
                else {
                    message.set("login fail !!");
                }
            });
        }
        else {
            message.set("username or password invalid");
        }

        //Dua thong tin user ra cho activity truyen qua activity khac
        loginViewModelInterface.onClickLogin();
    }

}
