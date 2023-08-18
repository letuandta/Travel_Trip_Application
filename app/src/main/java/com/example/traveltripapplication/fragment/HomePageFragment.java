package com.example.traveltripapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.traveltripapplication.MainPageActivity;
import com.example.traveltripapplication.R;
import com.example.traveltripapplication.adapter.CategoryAdapter;
import com.example.traveltripapplication.adapter.PlaceFamousAdapter;
import com.example.traveltripapplication.adapter.SearchAdapter;
import com.example.traveltripapplication.databinding.FragmentHomePageBinding;
import com.example.traveltripapplication.dialog.DatePickerDialog;
import com.example.traveltripapplication.model.CategoryModel;
import com.example.traveltripapplication.model.SearchModel;
import com.example.traveltripapplication.model.TourModel;
import com.example.traveltripapplication.model.UserModel;
import com.example.traveltripapplication.presenter.TourPresenter;
import com.example.traveltripapplication.viewmodel.SearchHomePageViewModel;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public class HomePageFragment extends Fragment implements PlaceFamousAdapter.PlaceFamounsAdapterListener, SearchHomePageViewModel.SearchHomePageListener {

    FragmentHomePageBinding fragmentHomePageBinding;
    ArrayList<CategoryModel> categoryModels;

    public HomePageFragment(UserModel userModel) {
        this.userModel = userModel;
    }

    private final UserModel userModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentHomePageBinding = FragmentHomePageBinding.inflate(inflater, container, false);
        fragmentHomePageBinding.setSearchHomePage(new SearchHomePageViewModel(this));
        return fragmentHomePageBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentHomePageBinding.fullnameuser7.setText(userModel.getFull_name());
        dataInitCate();

        CompletableFuture<ArrayList<TourModel>> future = TourPresenter.getTourHighRating();
        CopyOnWriteArrayList<TourModel> tours = new CopyOnWriteArrayList<>();
        future.thenAccept(tours::addAll);

        PlaceFamousAdapter placeFamousAdapter = new PlaceFamousAdapter(tours, this);
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);


        fragmentHomePageBinding.rcvPlaceFamous.setAdapter(placeFamousAdapter);
        fragmentHomePageBinding.rcvCategory.setAdapter(categoryAdapter);
        fragmentHomePageBinding.rcvPlaceFamous.setLayoutManager(linearLayoutManager);
        fragmentHomePageBinding.rcvCategory.setLayoutManager(linearLayoutManager1);
    }



    private void dataInitCate() {
        categoryModels = new ArrayList<CategoryModel>();
        categoryModels.add(new CategoryModel(1, "C001", "Trong nước", R.drawable.travel_country));
        categoryModels.add(new CategoryModel(2, "C002", "Quốc tế", R.drawable.travel_tour));
        categoryModels.add(new CategoryModel(4, "C004", "Tour nghĩ dưỡng", R.drawable.tour_relax));
        categoryModels.add(new CategoryModel(3, "C003", "Tour tham quan", R.drawable.travel_city));
        categoryModels.add(new CategoryModel(3, "C005", "Tour lịch sử", R.drawable.tour_history));
    }
    @Override
    public void onPlaceClick(TourModel tourModel) {
        if(tourModel.getTourActive() != 0) {
            Toast.makeText(getContext(), "click place", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(), "Địa điểm hiện đang tạm ngừng hoạt", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickSearchButton(SearchModel searchModel) {
        Toast.makeText(getContext(), "click search button", Toast.LENGTH_SHORT).show();
        ((MainPageActivity) requireActivity()).setSearchModel(searchModel);
        ((MainPageActivity) requireActivity()).getmActivityMainPageBinding().viewPager.setCurrentItem(1, true);
    }

    @Override
    public void chooseToDay() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(fragmentHomePageBinding.textToDay, getContext());
        fragmentHomePageBinding.textToDay.setOnClickListener(datePickerDialog::openDatePicker);
    }

    @Override
    public void choseFromDay() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(fragmentHomePageBinding.textFromDay, getContext());
        fragmentHomePageBinding.textFromDay.setOnClickListener(datePickerDialog::openDatePicker);
    }
}
