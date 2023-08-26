package com.example.traveltripapplication.admin.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.admin.adapter.TourAdapter;
import com.example.traveltripapplication.data.repository.TourRepository;
import com.example.traveltripapplication.databinding.FragmentTourBinding;
import com.example.traveltripapplication.model.TourModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;


public class TourFragment extends Fragment {

    FragmentTourBinding fragmentTourBinding;

    TourAdapter tourAdapter;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentTourBinding = FragmentTourBinding.inflate(inflater, container, false);
        return fragmentTourBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<TourModel> listTour = new ArrayList<>();
        listTour.add(new TourModel());
        CompletableFuture<ArrayList<TourModel>> completableFuture = TourRepository.getListCate();
        completableFuture.thenAcceptAsync(result -> {
            listTour.clear();
            listTour.addAll(result);
        });
        completableFuture.join();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        tourAdapter = new TourAdapter(listTour);
        fragmentTourBinding.rcListTour.setAdapter(tourAdapter);
        fragmentTourBinding.rcListTour.setLayoutManager(linearLayoutManager);
    }
}