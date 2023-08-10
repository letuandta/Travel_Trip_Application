package com.example.traveltripapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

        mActivityMainPageBinding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
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
}
