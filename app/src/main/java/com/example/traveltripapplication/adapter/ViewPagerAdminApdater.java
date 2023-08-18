package com.example.traveltripapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.traveltripapplication.fragment.HomePageFragment;
import com.example.traveltripapplication.model.UserModel;

public class ViewPagerAdminApdater extends FragmentStateAdapter {

    public ViewPagerAdminApdater(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, @NonNull UserModel userModel) {
        super(fragmentManager, lifecycle);
        this.userModel = userModel;
    }

    private final UserModel userModel;

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomePageFragment(userModel);
            case 1:
                return new HomePageFragment(userModel);
            case 2:
                return new HomePageFragment(userModel);
            case 3:
                return new HomePageFragment(userModel);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
