package com.example.traveltripapplication.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.traveltripapplication.admin.adapter.CateAdapter;
import com.example.traveltripapplication.data.repository.CateRepository;
import com.example.traveltripapplication.databinding.FragmentCategoryBinding;
import com.example.traveltripapplication.model.CategoryModel;
import com.example.traveltripapplication.viewmodel.AddCateViewModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class CateFragment extends Fragment implements AddCateViewModel.addCateViewModelListener {

    FragmentCategoryBinding fragmentCategoryBinding;
    CateAdapter cateAdapter;

    public static CategoryModel categoryModelUpdate = new CategoryModel();

    public static boolean checkUpdateCate =false;

    public static int position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentCategoryBinding = FragmentCategoryBinding.inflate(inflater, container, false);

        return fragmentCategoryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<CategoryModel> listCate = new ArrayList<>();
        listCate.add(new CategoryModel());
        CompletableFuture<ArrayList<CategoryModel>> completableFuture = CateRepository.getListCate();
        completableFuture.thenAccept(result -> {
            listCate.clear();
            listCate.addAll(result);
        });
        completableFuture.join();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        cateAdapter = new CateAdapter(listCate);
        fragmentCategoryBinding.rcListCate.setAdapter(cateAdapter);
        fragmentCategoryBinding.rcListCate.setLayoutManager(linearLayoutManager);
        fragmentCategoryBinding.btnAdd.setOnClickListener(view1 -> {
            AddCateFragment addCateFragment = AddCateFragment.newInstanse(this);
            addCateFragment.show(requireActivity().getSupportFragmentManager(), "addCate");
        });
    }

    @Override
    public void successAddCate(CategoryModel categoryModel) {
        cateAdapter.loadDataLastPosition(categoryModel);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (CateFragment.checkUpdateCate) {
            cateAdapter.updateData(CateFragment.categoryModelUpdate, position);
            CateFragment.checkUpdateCate = false;
        }
    }
}
