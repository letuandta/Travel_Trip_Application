package com.example.traveltripapplication.presenter;

import com.example.traveltripapplication.enumapp.LoginEnum;

import java.util.concurrent.CompletableFuture;

public class AuthPresenter {

    //region for repository
    //****


    //region for method
    public static CompletableFuture<Boolean> Login(String username, String password, LoginEnum loginEnum){
        CompletableFuture<Boolean> loginFuture = new CompletableFuture<>();

        //region for check authentication


        loginFuture.complete(true);
        return loginFuture;
    }

    public static CompletableFuture<Boolean> Logout(){
        CompletableFuture<Boolean> logoutFuture = new CompletableFuture<>();

        //region for logout thread

        logoutFuture.complete(true);
        return logoutFuture;
    }
}
