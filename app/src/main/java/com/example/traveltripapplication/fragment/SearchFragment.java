package com.example.traveltripapplication.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false);
        return mFragmentSearchBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        searchModel = new SearchModel();
        searchModel = ((MainPageActivity) requireActivity()).getSearchModel();
        boolean isOtherSearch = ((MainPageActivity) requireActivity()).getOtherSearch();
        if (searchModel != null && isOtherSearch) {
            ((MainPageActivity) requireActivity()).setOtherSearch(false);
            mFragmentSearchBinding.textLocation.setText(searchModel.getLocation());
            String toFromDay = "Từ " + searchModel.getToDay().substring(0, 5) + " Đến " + searchModel.getFromDay().substring(0, 5);
            mFragmentSearchBinding.textDateRange.setText(String.valueOf(toFromDay));

            CompletableFuture<ArrayList<TourModel>> future = TourPresenter.getToursSearch(searchModel.getLocation());
            CopyOnWriteArrayList<TourModel> tours = new CopyOnWriteArrayList<>();
            future.thenAccept(tours::addAll);

            SearchAdapter searchAdapter = new SearchAdapter(tours, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            mFragmentSearchBinding.rcvResultSearch.setLayoutManager(linearLayoutManager);
            mFragmentSearchBinding.rcvResultSearch.setAdapter(null);
            mFragmentSearchBinding.rcvResultSearch.setAdapter(searchAdapter);
        }
    }


    @Override
    public void onClickItem(TourModel tourModel) {

    }
}
