package com.example.traveltripapplication.admin.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.traveltripapplication.adapter.CategoryAdapter;
import com.example.traveltripapplication.data.repository.CateRepository;
import com.example.traveltripapplication.databinding.FragmentCategoryBinding;
import com.example.traveltripapplication.model.CategoryModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;


public class CategoryFragment extends Fragment {

    FragmentCategoryBinding fragmentCategoryBinding;

    CategoryAdapter categoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentCategoryBinding = FragmentCategoryBinding.inflate(inflater, container, false);

        return fragmentCategoryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<CategoryModel> listCate = new ArrayList<>();
        listCate.add(new CategoryModel());
        CompletableFuture<ArrayList<CategoryModel>> completableFuture = CateRepository.getListCate();
        completableFuture.thenAccept(result -> {
            listCate.clear();
            listCate.addAll(result);
        });
        completableFuture.join();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        categoryAdapter = new CategoryAdapter(listCate);

    }
}