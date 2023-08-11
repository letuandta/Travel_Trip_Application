package com.example.traveltripapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.traveltripapplication.adapter.PlaceFamousAdapter;
import com.example.traveltripapplication.databinding.FragmentHomePageBinding;
import com.example.traveltripapplication.model.PlaceFamousModel;
import java.util.ArrayList;

public class HomePageFragment extends Fragment {

    private com.example.traveltripapplication.databinding.FragmentHomePageBinding fragmentHomePageBinding;

    ArrayList<PlaceFamousModel> placeFamousModels;
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

        PlaceFamousAdapter placeFamousAdapter = new PlaceFamousAdapter(placeFamousModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        fragmentHomePageBinding.rcvPlaceFamous.setAdapter(placeFamousAdapter);
        fragmentHomePageBinding.rcvPlaceFamous.setLayoutManager(linearLayoutManager);

    }

    private void dataInit() {
        placeFamousModels = new ArrayList<PlaceFamousModel>();
        placeFamousModels.add(new PlaceFamousModel(1, "Đà Nẵng", "Việt Nam",
                "https://i.pinimg.com/564x/58/bd/7c/58bd7c653d1fc5d227ca8fad58232b2a.jpg", 0));
        placeFamousModels.add(new PlaceFamousModel(2, "Đà Nẵng", "Việt Nam",
                "https://i.pinimg.com/564x/58/bd/7c/58bd7c653d1fc5d227ca8fad58232b2a.jpg", 1));
        placeFamousModels.add(new PlaceFamousModel(3, "Đà Nẵng", "Việt Nam",
                "https://i.pinimg.com/564x/58/bd/7c/58bd7c653d1fc5d227ca8fad58232b2a.jpg", 1));
        placeFamousModels.add(new PlaceFamousModel(4, "Đà Nẵng", "Việt Nam",
                "https://i.pinimg.com/564x/58/bd/7c/58bd7c653d1fc5d227ca8fad58232b2a.jpg", 0));
        placeFamousModels.add(new PlaceFamousModel(5, "Đà Nẵng", "Việt Nam",
                "https://i.pinimg.com/564x/58/bd/7c/58bd7c653d1fc5d227ca8fad58232b2a.jpg", 1));
    }
}
