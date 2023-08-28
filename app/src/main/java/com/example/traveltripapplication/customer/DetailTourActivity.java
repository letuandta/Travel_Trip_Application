package com.example.traveltripapplication.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.customer.adapter.ViewPagerDetailTourAdapter;
import com.example.traveltripapplication.databinding.ActivityDetailTourBinding;
import com.example.traveltripapplication.model.TourModel;
import com.example.traveltripapplication.viewmodel.DetailTourViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import eightbitlab.com.blurview.RenderScriptBlur;

public class DetailTourActivity extends AppCompatActivity {

    ActivityDetailTourBinding binding;
    TourModel mTourModel;

    int currentSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailTourBinding.inflate(getLayoutInflater());

        if (getIntent().getExtras().getSerializable("tour") instanceof TourModel) {
            mTourModel = (TourModel) getIntent().getExtras().getSerializable("tour");
        }
        DetailTourViewModel detailTourViewModel = new DetailTourViewModel(mTourModel);
        binding.setDetailTour(detailTourViewModel);
        setContentView(binding.getRoot());

        binding.imgArrowBack.setOnClickListener(view -> this.onBackPressed());
        Picasso.get().load(mTourModel.getThumbnail()).into(binding.imgPlace);
        blurBackground();

        ArrayList<TextView> tag = new ArrayList<>();
        tag.add(binding.tagOverview);
        tag.add(binding.tagItinerary);
        tag.add(binding.tagTicket);
        tag.add(binding.tagRating);
        changeColor(binding.tagOverview, tag, 0);
        setupViewPager(tag);

        binding.tagOverview.setOnClickListener(view -> {
            changeColor((TextView) view, tag, 0);
        });
        binding.tagItinerary.setOnClickListener(view -> {
            changeColor((TextView) view, tag, 1);
        });binding.tagTicket.setOnClickListener(view -> {
            changeColor((TextView) view, tag, 2);
        });binding.tagRating.setOnClickListener(view -> {
            changeColor((TextView) view, tag, 3);
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void changeColor(TextView textView, ArrayList<TextView> tag, int position){
        TextView currentTextView = tag.get(currentSelected);
        currentTextView.setBackground(getDrawable(R.drawable.shape_button));
        textView.setBackground(getDrawable(R.drawable.shape_button_selected));
        currentSelected = position;
        binding.viewPager.setCurrentItem(position);
    }

    private void setupViewPager(ArrayList<TextView> tag) {
        ViewPagerDetailTourAdapter viewPagerAdapter = new ViewPagerDetailTourAdapter(getSupportFragmentManager(), getLifecycle(), this.mTourModel);
        binding.viewPager.setAdapter(viewPagerAdapter);

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        changeColor(tag.get(0), tag, 0);
                        break;
                    case 1:
                        changeColor(tag.get(1), tag, 1);
                        break;
                    case 2:
                        changeColor(tag.get(2), tag, 2);
                        break;
                    case 3:
                        changeColor(tag.get(3), tag, 3);
                        break;
                }
            }
        });
    }

    public void blurBackground(){
        float radius = 10f;

        View decorView = getWindow().getDecorView();
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);

        Drawable windowBackground = decorView.getBackground();

        binding.blurView.setupWith(rootView, new RenderScriptBlur(this))
                .setFrameClearDrawable(windowBackground)
                .setBlurRadius(radius);
    }

}