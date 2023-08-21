package com.example.traveltripapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.traveltripapplication.databinding.ActivityDetailTourBinding;
import com.example.traveltripapplication.model.TourModel;
import com.example.traveltripapplication.viewmodel.DetailTourViewModel;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailTourActivity extends AppCompatActivity {

    ActivityDetailTourBinding binding;
    TourModel mTourModel;

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
    }

}