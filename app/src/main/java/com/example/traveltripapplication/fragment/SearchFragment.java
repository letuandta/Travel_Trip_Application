package com.example.traveltripapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.traveltripapplication.DetailTourActivity;
import com.example.traveltripapplication.MainPageActivity;
import com.example.traveltripapplication.adapter.SearchAdapter;
import com.example.traveltripapplication.databinding.FragmentSearchBinding;
import com.example.traveltripapplication.model.SearchModel;
import com.example.traveltripapplication.model.TourModel;
import com.example.traveltripapplication.presenter.TourPresenter;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public class SearchFragment extends Fragment implements SearchAdapter.SearchAdapterListener {

    FragmentSearchBinding mFragmentSearchBinding;
    private SearchModel searchModel;
    private SearchAdapter searchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false);
        return mFragmentSearchBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchAdapter = new SearchAdapter( this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mFragmentSearchBinding.rcvResultSearch.setLayoutManager(linearLayoutManager);
        mFragmentSearchBinding.rcvResultSearch.smoothScrollToPosition(0);
        mFragmentSearchBinding.rcvResultSearch.setAdapter(searchAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        searchModel = new SearchModel();
        searchModel = ((MainPageActivity) requireActivity()).getSearchModel();
        boolean isOtherSearch = ((MainPageActivity) requireActivity()).getOtherSearch();
        boolean isCateSearch = ((MainPageActivity) requireActivity()).getCateSearch();
        Log.d("IS CATEGORY SEARCH", "" + isCateSearch);
        if (searchModel != null && isOtherSearch) {
            ((MainPageActivity) requireActivity()).setOtherSearch(false);
            mFragmentSearchBinding.textLocation.setText(searchModel.getLocation());
            String toFromDay = "Từ " + searchModel.getToDay().substring(0, 5) + " Đến " + searchModel.getFromDay().substring(0, 5);
            mFragmentSearchBinding.textDateRange.setText(String.valueOf(toFromDay));

            CompletableFuture<ArrayList<TourModel>> future = TourPresenter.getToursSearch(searchModel.getLocation());
            ArrayList<TourModel> tours = new ArrayList<>();
            future.thenAcceptAsync(result -> {
                tours.addAll(result);
                Log.d("Tour size", " " + tours.size());
                searchAdapter.updateData(tours);
                mFragmentSearchBinding.rcvResultSearch.smoothScrollToPosition(0);
            });
        } else if (isCateSearch) {
            ((MainPageActivity) requireActivity()).setCateSearch(false);
            long cateId = ((MainPageActivity) requireActivity()).getCategoryId();
            mFragmentSearchBinding.textLocation.setText("Danh mục");

            CompletableFuture<ArrayList<TourModel>> future = TourPresenter.getToursByCategoryId(cateId);
            ArrayList<TourModel> tours = new ArrayList<>();
            future.thenAcceptAsync(result -> {
                tours.addAll(result);
                Log.d("Tour size", " " + tours.size());
                searchAdapter.updateData(tours);
                mFragmentSearchBinding.rcvResultSearch.smoothScrollToPosition(0);
            });
        }
    }


    @Override
    public void onClickItem(TourModel tourModel) {
        if(tourModel.getTourActive() != 0) {
            Toast.makeText(getContext(), "click place", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), DetailTourActivity.class);
            intent.putExtra("tour", tourModel);
            startActivity(intent);
        }
        else Toast.makeText(getContext(), "Địa điểm hiện đang tạm ngừng hoạt", Toast.LENGTH_SHORT).show();
    }
}
