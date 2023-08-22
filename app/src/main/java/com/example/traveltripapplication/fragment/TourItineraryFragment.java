package com.example.traveltripapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.traveltripapplication.adapter.TourItineraryAdapter;
import com.example.traveltripapplication.databinding.FragmentDetailItineraryBinding;
import com.example.traveltripapplication.model.TourItineraryModel;
import com.example.traveltripapplication.presenter.TourPresenter;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TourItineraryFragment extends Fragment {

    FragmentDetailItineraryBinding binding;
    long tourId;

    public TourItineraryFragment(long tourId){
        this.tourId = tourId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailItineraryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<TourItineraryModel> tourItineraryModels = new ArrayList<>();
        tourItineraryModels.add(new TourItineraryModel());
        CompletableFuture<ArrayList<TourItineraryModel>> future = TourPresenter.getItineraryByTourId(tourId);
        try {
            tourItineraryModels.clear();
            tourItineraryModels.addAll(future.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        TourItineraryAdapter adapter = new TourItineraryAdapter(tourItineraryModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rcvItinerary.setAdapter(adapter);
        binding.rcvItinerary.setLayoutManager(linearLayoutManager);

    }
}
