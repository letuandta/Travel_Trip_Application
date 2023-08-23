package com.example.traveltripapplication.admin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.admin.adapter.ViewPagerAdminApdater;
import com.example.traveltripapplication.databinding.ActivityAdminBinding;
import com.example.traveltripapplication.model.UserModel;
import com.google.android.material.navigation.NavigationBarView;

public class AdminActivity extends AppCompatActivity {
    ActivityAdminBinding mActivityAdminBinding;


    private UserModel userModel;
    private UserModel userAdded;
    private UserModel userUpdate;
    int position;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    boolean flag = false;
    public UserModel getUserModel() {
        return userModel;
    }

    public UserModel getUserAdded() {
        return userAdded;
    }

    public UserModel getUserUpdate() {
        return userUpdate;
    }

    public int getPosition() {
        return position;
    }

    public void setUserAdded(UserModel userAdded) {
        this.userAdded = userAdded;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAdminBinding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(mActivityAdminBinding.getRoot());
            UserModel userModel;
            userModel = (UserModel) getIntent().getExtras().getSerializable("user");
        this.userModel = userModel;
            setupViewPager();

        mActivityAdminBinding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.manager_user) {
                    mActivityAdminBinding.viewPager.setCurrentItem(0);
                    return true;
                } else if (item.getItemId() == R.id.manager_cate) {
                    mActivityAdminBinding.viewPager.setCurrentItem(1);
                    return true;
                } else if (item.getItemId() == R.id.manager_order) {
                    mActivityAdminBinding.viewPager.setCurrentItem(2);
                    return true;
                } else {
                    mActivityAdminBinding.viewPager.setCurrentItem(3);
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

    @Override
    protected void onResume() {
        super.onResume();
        this.flag = true;
    }

}