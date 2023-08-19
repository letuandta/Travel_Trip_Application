package com.example.traveltripapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.traveltripapplication.adapter.ViewPagerAdminApdater;
import com.example.traveltripapplication.databinding.ActivityAdminBinding;
import com.example.traveltripapplication.model.UserModel;
import com.google.android.material.navigation.NavigationBarView;

public class AdminActivity extends AppCompatActivity {
    ActivityAdminBinding mActivityAdminBinding;

    private UserModel userModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAdminBinding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(mActivityAdminBinding.getRoot());
            UserModel userModel;
            userModel = getIntent().getExtras().getParcelable("user");
        this.userModel = userModel;
            setupViewPager();

        mActivityAdminBinding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    mActivityAdminBinding.viewPager.setCurrentItem(0);
                    return true;
                } else if (item.getItemId() == R.id.action_search_history) {
                    return true;
                } else if (item.getItemId() == R.id.action_favorite) {
                    return true;
                } else {
                    return true;
                }
            }
        });
    }

    private void setupViewPager() {
        ViewPagerAdminApdater viewPagerAdminApdater = new ViewPagerAdminApdater(getSupportFragmentManager(), getLifecycle(), this.userModel);
        mActivityAdminBinding.viewPager.setAdapter(viewPagerAdminApdater);

        mActivityAdminBinding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mActivityAdminBinding.bottomNav.getMenu().findItem(R.id.manager_user).setChecked(true);
                        break;
                    case 1:
                        mActivityAdminBinding.bottomNav.getMenu().findItem(R.id.manager_cate).setChecked(true);
                        break;
                    case 2:
                        mActivityAdminBinding.bottomNav.getMenu().findItem(R.id.manager_order).setChecked(true);
                        break;
                    case 3:
                        mActivityAdminBinding.bottomNav.getMenu().findItem(R.id.manager_tour).setChecked(true);
                        break;
                }
            }
        });
    }
}