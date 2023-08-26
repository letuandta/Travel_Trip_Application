package com.example.traveltripapplication.admin.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ContentInfoCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.databinding.FragmentCategoryAddBinding;
import com.example.traveltripapplication.databinding.FragmentCategoryBinding;
import com.example.traveltripapplication.model.CategoryModel;
import com.example.traveltripapplication.viewmodel.AddCateViewModel;

public class AddCateFragment extends DialogFragment implements AddCateViewModel.addCateViewModelListener{

    private FragmentCategoryAddBinding binding;

    public static boolean checkCreateCate = false;
    AddCateViewModel addCateViewModel = new AddCateViewModel(this);

    AddCateViewModel.addCateViewModelListener addCateViewModelListener;
    public AddCateFragment(AddCateViewModel.addCateViewModelListener addCateViewModelListener) {
        this.addCateViewModelListener = addCateViewModelListener;
    }

    public static AddCateFragment newInstanse(AddCateViewModel.addCateViewModelListener addCateViewModelListener) {
        final AddCateFragment dialog = new AddCateFragment(addCateViewModelListener);
        return dialog;
    }

    @Nullable
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_category_add, null, false);
        binding.setAddCate(addCateViewModel);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(binding.getRoot());
        return builder.create();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        if (AddCateFragment.checkCreateCate) {
            addCateViewModelListener.successAddCate(addCateViewModel.getCategoryModel());
            AddCateFragment.checkCreateCate = false;
        }
    }

    @Override
    public void successAddCate(CategoryModel categoryModel) {
        this.dismiss();
    }
}