package com.example.traveltripapplication.data.repository;

import com.example.traveltripapplication.data.database.DatabaseHelper;
import com.example.traveltripapplication.model.RatingModel;
import com.example.traveltripapplication.model.TourItineraryModel;
import com.example.traveltripapplication.model.TourModel;
import com.example.traveltripapplication.model.TourTicketModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class TourRepository {

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

    public static CompletableFuture<TourModel> getTourById(long id){
        CompletableFuture<TourModel> future = new CompletableFuture<>();
        future = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mTourHelper().getTourByTourID(id);
        });
        return future;
    }

    public static CompletableFuture<ArrayList<TourItineraryModel>> getItineraryByTourId(long id){
        CompletableFuture<ArrayList<TourItineraryModel>> future = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mTourItineraryHelper().getItineraryByTourId(id);
        });

        return future;
    }

    public static CompletableFuture<ArrayList<TourTicketModel>> getTicketByTourId(long id){
        CompletableFuture<ArrayList<TourTicketModel>> future = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mTourTicketHelper().getTicketByTourID(id);
        });
        return future;
    }

    public static CompletableFuture<ArrayList<TourModel>> getListCate() {
        CompletableFuture<ArrayList<TourModel>> future = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mTourHelper().getListTour();
        });
        return future;
    }

    public static CompletableFuture<Long> createTour(TourModel tourModel) {
        CompletableFuture<Long> registerFuture = new CompletableFuture<>();
        registerFuture = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mTourHelper().insert(tourModel);
        });
        return registerFuture;
    }

    public static CompletableFuture<ArrayList<RatingModel>> getRatingByTourId(long tourId, int currentPage){
        CompletableFuture<ArrayList<RatingModel>> future = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mRatingHelper().getRatingByTourId(tourId, currentPage);
        });

        return future;
    }

    public static CompletableFuture<Integer> getCommentCountByTourId(long tourId){
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mRatingHelper().getCommentCountByTourId(tourId);
        });

        return future;
    }

    public static CompletableFuture<Long> createRating(RatingModel ratingModel){
        return CompletableFuture.supplyAsync(() -> DatabaseHelper.mRatingHelper().insert(ratingModel));
    }
}
