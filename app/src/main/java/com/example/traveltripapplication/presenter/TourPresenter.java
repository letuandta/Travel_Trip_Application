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

    public static CompletableFuture<ArrayList<TourModel>> getToursSearch(String location){
        CompletableFuture<ArrayList<TourModel>> future = new CompletableFuture<>();
        future = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mTourHelper().getTourByLocation(location);
        });
        return future;
    }

    public static CompletableFuture<ArrayList<TourModel>> getToursByCategoryId(Long cateId){
        CompletableFuture<ArrayList<TourModel>> future = new CompletableFuture<>();
        future = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mTourHelper().getTourByCategoryId(cateId);
        });
        return future;
    }
}
