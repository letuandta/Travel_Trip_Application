package com.example.traveltripapplication.viewmodel;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.traveltripapplication.BR;
import com.example.traveltripapplication.model.UserModel;
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
        if((getLoginModel().isEmailValid() || getLoginModel().isUserNameValid()) && getLoginModel().isPasswordValid()) {
            Log.d("LOGIN", getLoginModel().getUsername() + getLoginModel().getPassword());
            CompletableFuture<UserModel> loginFuture = AuthPresenter.Login(getLoginModel().getUsername(), getLoginModel().getPassword(),
                    getLoginModel().isEmailValid() ? LoginEnum.EMAIL : LoginEnum.USERNAME);
            loginFuture.thenAcceptAsync(result -> {
                if(result.get_ID() != -1){
                    message.set("Login success !!");
                    //Gan gia tri cho user
                    //Dua thong tin user ra cho activity truyen qua activity khac
                    loginViewModelInterface.onClickLogin(result);
                }
                else {
                    message.set("login fail !!");
                }
            });
            loginFuture.thenRun(() -> {
                message.set("Processing.......");
            });
        }
        else {
            message.set("username or password invalid");
        }
    }

}
