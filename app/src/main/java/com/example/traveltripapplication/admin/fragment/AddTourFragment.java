package com.example.traveltripapplication.admin.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.databinding.FragmentTourAddBinding;
import com.example.traveltripapplication.model.TourModel;
import com.example.traveltripapplication.viewmodel.AddTourViewModel;

public class AddTourFragment extends DialogFragment implements AddTourViewModel.addTourViewModelListener{

    private FragmentTourAddBinding binding;

    public static boolean checkCreateCate = false;

    AddTourViewModel addTourViewModel = new AddTourViewModel(this);
    public AddTourFragment(AddTourFragment.addTourViewModelListener addTourViewModelListener) {
        this.addTourViewModelListener = addTourViewModelListener;
    }
    private addTourViewModelListener addTourViewModelListener;

    public static AddTourFragment newInstanse(addTourViewModelListener addTourViewModelListener) {
        final AddTourFragment dialog = new AddTourFragment(addTourViewModelListener);
        return dialog;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_tour_add, null, false);
        binding.setAddTour(addTourViewModel);
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
        addTourViewModelListener.successAddTour(addTourViewModel.getTourModel());
    }
    @Override
    public void successAddTour(TourModel tourModel) {
        this.dismiss();
    }

    public interface addTourViewModelListener {
        public void successAddTour(TourModel tourModel);
    }
}