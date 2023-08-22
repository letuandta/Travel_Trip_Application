package com.example.traveltripapplication.data.repository;

import com.example.traveltripapplication.data.database.DatabaseHelper;
import com.example.traveltripapplication.model.UserModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class UserRepository {
    public static CompletableFuture<ArrayList<UserModel>> getListUser() {
        CompletableFuture<ArrayList<UserModel>> future = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mUserHelper().getListUser();
        });
        return future;
    }

}
