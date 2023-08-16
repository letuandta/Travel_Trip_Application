package com.example.traveltripapplication.presenter;

import com.example.traveltripapplication.database.DatabaseHelper;
import com.example.traveltripapplication.model.TourModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class TourPresenter {

    public static CompletableFuture<ArrayList<TourModel>> getTourHighRating(){
        CompletableFuture<ArrayList<TourModel>> future = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mTourHelper().getTourHighRating();
        });
        return future;
    }
}
