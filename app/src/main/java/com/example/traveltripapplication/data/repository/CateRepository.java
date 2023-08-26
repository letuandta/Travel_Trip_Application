package com.example.traveltripapplication.data.repository;

import com.example.traveltripapplication.data.database.DatabaseHelper;
import com.example.traveltripapplication.model.CategoryModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class CateRepository {
    public static CompletableFuture<ArrayList<CategoryModel>> getListCate() {
        CompletableFuture<ArrayList<CategoryModel>> future = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mCategoryHelper().getListCate();
        });
        return future;
    }

    public static CompletableFuture<Long> createCate(CategoryModel categoryModel) {
        CompletableFuture<Long> registerFuture = new CompletableFuture<>();
        registerFuture = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mCategoryHelper().insert(categoryModel);
        });

        return registerFuture;
    }
}
