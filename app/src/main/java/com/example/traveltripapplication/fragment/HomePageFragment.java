package com.example.traveltripapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.traveltripapplication.adapter.CategoryAdapter;
import com.example.traveltripapplication.adapter.PlaceFamousAdapter;
import com.example.traveltripapplication.databinding.FragmentHomePageBinding;
import com.example.traveltripapplication.model.CategoryModel;
import com.example.traveltripapplication.model.PlaceFamousModel;
import java.util.ArrayList;

public class HomePageFragment extends Fragment {

    private com.example.traveltripapplication.databinding.FragmentHomePageBinding fragmentHomePageBinding;

    ArrayList<PlaceFamousModel> placeFamousModels;
    ArrayList<CategoryModel> categoryModels;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentHomePageBinding = FragmentHomePageBinding.inflate(inflater, container, false);
        return fragmentHomePageBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataInit();
        dataInitCate();

        PlaceFamousAdapter placeFamousAdapter = new PlaceFamousAdapter(placeFamousModels);
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);


        fragmentHomePageBinding.rcvPlaceFamous.setAdapter(placeFamousAdapter);
        fragmentHomePageBinding.rcvCategory.setAdapter(categoryAdapter);
        fragmentHomePageBinding.rcvPlaceFamous.setLayoutManager(linearLayoutManager);
        fragmentHomePageBinding.rcvCategory.setLayoutManager(linearLayoutManager1);
    }

    private void dataInit() {
        placeFamousModels = new ArrayList<PlaceFamousModel>();
        placeFamousModels.add(new PlaceFamousModel(1, "Đà Nẵng", "Việt Nam",
                "https://i.pinimg.com/564x/58/bd/7c/58bd7c653d1fc5d227ca8fad58232b2a.jpg", 0));
        placeFamousModels.add(new PlaceFamousModel(2, "Đà Nẵng", "Việt Nam",
                "https://i.pinimg.com/564x/f1/79/13/f17913041c2620f024fdea7406706ac7.jpg", 1));
        placeFamousModels.add(new PlaceFamousModel(3, "Đà Nẵng", "Việt Nam",
                "https://i.pinimg.com/564x/a6/0f/0b/a60f0bc22fe23fc9072722398c4a11cc.jpg", 1));
        placeFamousModels.add(new PlaceFamousModel(4, "Đà Nẵng", "Việt Nam",
                "https://i.pinimg.com/564x/58/bd/7c/58bd7c653d1fc5d227ca8fad58232b2a.jpg", 0));
        placeFamousModels.add(new PlaceFamousModel(5, "Đà Nẵng", "Việt Nam",
                "https://i.pinimg.com/564x/58/bd/7c/58bd7c653d1fc5d227ca8fad58232b2a.jpg", 1));
    }

    private void dataInitCate() {
        categoryModels = new ArrayList<CategoryModel>();
        categoryModels.add(new CategoryModel(1, "https://i.pinimg.com/564x/f1/79/13/f17913041c2620f024fdea7406706ac7.jpg","Quốc Gia" ));
        categoryModels.add(new CategoryModel(2, "https://i.pinimg.com/564x/f1/79/13/f17913041c2620f024fdea7406706ac7.jpg","Tỉnh" ));
        categoryModels.add(new CategoryModel(3, "https://i.pinimg.com/564x/f1/79/13/f17913041c2620f024fdea7406706ac7.jpg","Tour" ));
    }

}
