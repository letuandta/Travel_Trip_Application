package com.example.traveltripapplication.presenter;

import com.example.traveltripapplication.database.DatabaseHelper;
import com.example.traveltripapplication.enumapp.LoginEnum;
import com.example.traveltripapplication.model.UserModel;

import java.util.concurrent.CompletableFuture;

public class AuthPresenter {

    //region for repository
    //****


    //region for method
    public static CompletableFuture<UserModel> Login(String username, String password, LoginEnum loginEnum){
        CompletableFuture<UserModel> loginFuture = new CompletableFuture<>();
        UserModel user = new UserModel();
        if(loginEnum == LoginEnum.USERNAME){
            loginFuture = CompletableFuture.supplyAsync(() -> {
                return DatabaseHelper.mUserHelper().getUserByUsernameAndPassword(username, password);
            });
        }
        else if(loginEnum == LoginEnum.EMAIL){
            loginFuture = CompletableFuture.supplyAsync(() -> {
                return DatabaseHelper.mUserHelper().getUserByEmailAndPassword(username, password);
            });
        }
        return loginFuture;
    }

    public static CompletableFuture<Boolean> Logout(){
        CompletableFuture<Boolean> logoutFuture = new CompletableFuture<>();

        //region for logout thread

        logoutFuture.complete(true);
        return logoutFuture;
    }
}
