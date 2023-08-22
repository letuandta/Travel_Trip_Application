package com.example.traveltripapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.traveltripapplication.fragment.HomePageFragment;
import com.example.traveltripapplication.fragment.SearchFragment;
import com.example.traveltripapplication.fragment.TourItineraryFragment;
import com.example.traveltripapplication.fragment.TourOverViewFragment;
import com.example.traveltripapplication.model.TourModel;
import com.example.traveltripapplication.model.UserModel;

import java.util.Arrays;
import java.util.List;

public class ViewPagerDetailTourAdapter extends FragmentStateAdapter {


    public ViewPagerDetailTourAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,
                            @NonNull TourModel tourModel) {
        super(fragmentManager, lifecycle);
        this.fragments = Arrays.asList(
                new TourOverViewFragment(tourModel.getExperience(), tourModel.getMoreInfo(), String.valueOf(tourModel.getTourDuration())),
                new TourItineraryFragment(tourModel.getTourID())
        );
    }
    private final List<Fragment> fragments;
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
