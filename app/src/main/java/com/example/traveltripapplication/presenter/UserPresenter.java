package com.example.traveltripapplication.presenter;

import com.example.traveltripapplication.database.DatabaseHelper;
import com.example.traveltripapplication.model.UserModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class UserPresenter {
    public static CompletableFuture<ArrayList<UserModel>> getListUser() {
        CompletableFuture<ArrayList<UserModel>> future = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mUserHelper().getListUser();
        });
        return future;
    }

}
