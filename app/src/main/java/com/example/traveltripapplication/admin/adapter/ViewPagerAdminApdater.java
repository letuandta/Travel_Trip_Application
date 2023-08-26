package com.example.traveltripapplication.admin.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.traveltripapplication.admin.fragment.CateFragment;
import com.example.traveltripapplication.admin.fragment.TourFragment;
import com.example.traveltripapplication.admin.fragment.UserFragment;
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
                return new UserFragment();
            case 1:
                return new CateFragment();
            case 2:
                return new TourFragment();

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

