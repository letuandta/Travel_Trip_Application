package com.example.traveltripapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.traveltripapplication.adapter.ViewPagerAdapter;
import com.example.traveltripapplication.databinding.ActivityMainPageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainPageActivity extends AppCompatActivity {

    ActivityMainPageBinding mActivityMainPageBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainPageBinding = ActivityMainPageBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainPageBinding.getRoot());

        setupViewPager();

        mActivityMainPageBinding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    mActivityMainPageBinding.viewPager.setCurrentItem(0);
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
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        mActivityMainPageBinding.viewPager.setAdapter(viewPagerAdapter);

        mActivityMainPageBinding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mActivityMainPageBinding.bottomNav.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        mActivityMainPageBinding.bottomNav.getMenu().findItem(R.id.action_search_history).setChecked(true);
                        break;
                    case 2:
                        mActivityMainPageBinding.bottomNav.getMenu().findItem(R.id.action_favorite).setChecked(true);
                        break;
                    case 3:
                        mActivityMainPageBinding.bottomNav.getMenu().findItem(R.id.action_setting).setChecked(true);
                        break;
                }
            }
        });
    }
}
