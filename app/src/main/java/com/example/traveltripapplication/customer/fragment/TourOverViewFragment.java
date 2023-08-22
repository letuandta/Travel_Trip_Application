package com.example.traveltripapplication.customer.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.traveltripapplication.databinding.FragmentDeatilOverviewBinding;

public class TourOverViewFragment extends Fragment {

    FragmentDeatilOverviewBinding binding;
    private final String experience;
    private final String moreInfo;
    private final String duration;

    public TourOverViewFragment(String experience, String moreInfo, String duration) {
        this.experience = experience;
        this.moreInfo = moreInfo;
        this.duration = duration;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDeatilOverviewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvExperience.setText(String.valueOf(experience));
        binding.tvMoreInfo.setText(String.valueOf(moreInfo));
        binding.tvDuration.setText(String.valueOf(duration + "Ngày"));
    }
}
