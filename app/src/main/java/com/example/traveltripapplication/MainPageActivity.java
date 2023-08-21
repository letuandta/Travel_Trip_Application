package com.example.traveltripapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.traveltripapplication.adapter.ViewPagerAdapter;
import com.example.traveltripapplication.databinding.ActivityMainPageBinding;
import com.example.traveltripapplication.model.SearchModel;
import com.example.traveltripapplication.model.UserModel;
import com.google.android.material.navigation.NavigationBarView;

public class MainPageActivity extends AppCompatActivity {

    public ActivityMainPageBinding getmActivityMainPageBinding() {
        return mActivityMainPageBinding;
    }

    ActivityMainPageBinding mActivityMainPageBinding;

    private UserModel userModel;

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
        this.isOtherSearch = true;
        this.isCateSearch = false;
    }

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public Boolean getOtherSearch() {
        return isOtherSearch;
    }

    public void setOtherSearch(Boolean otherSearch) {
        this.isOtherSearch = otherSearch;
    }

    public Boolean getCateSearch() {
        return isCateSearch;
    }

    public void setCateSearch(Boolean cateSearch) {
        this.isCateSearch = cateSearch;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
        this.isCateSearch = true;
        this.isOtherSearch = false;
    }

    private SearchModel searchModel;
    private long categoryId;
    private Boolean isOtherSearch = false;

    private Boolean isCateSearch = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainPageBinding = ActivityMainPageBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainPageBinding.getRoot());
        UserModel userModel;
        userModel = getIntent().getExtras().getParcelable("user");
        this.userModel = userModel;
        setupViewPager();

        mActivityMainPageBinding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    mActivityMainPageBinding.viewPager.setCurrentItem(0);
                    return true;
                } else if (item.getItemId() == R.id.action_search_history) {
                    mActivityMainPageBinding.viewPager.setCurrentItem(1);
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
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), this.userModel);
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
