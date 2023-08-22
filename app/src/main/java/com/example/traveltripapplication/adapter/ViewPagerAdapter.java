package com.example.traveltripapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.traveltripapplication.customer.fragment.HomePageFragment;
import com.example.traveltripapplication.customer.fragment.SearchFragment;
import com.example.traveltripapplication.model.UserModel;

import java.util.Arrays;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,
                            @NonNull UserModel userModel) {
        super(fragmentManager, lifecycle);
        this.userModel = userModel;

        this.fragments = Arrays.asList(
                new HomePageFragment(userModel),
                new SearchFragment(),
                new HomePageFragment(userModel),
                new HomePageFragment(userModel)
        );
    }

    private final UserModel userModel;

    private final List<Fragment> fragments;


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public Fragment getFragment(int position) {
        return fragments.get(position);
    }
}
