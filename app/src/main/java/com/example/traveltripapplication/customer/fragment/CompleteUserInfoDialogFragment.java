package com.example.traveltripapplication.customer.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.databinding.FragmentUpdateUserBinding;
import com.example.traveltripapplication.viewmodel.interfaceListener.CompleteUserInfoInterface;
import com.example.traveltripapplication.model.UserModel;
import com.example.traveltripapplication.viewmodel.CompleteUserInfoViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class CompleteUserInfoDialogFragment extends DialogFragment{

    private UserModel user;
    private FragmentUpdateUserBinding binding;
    private  CompleteUserInfoInterface completeUserInfoInterface;

    public void setCompleteUserInfoInterface(CompleteUserInfoInterface completeUserInfoInterface) {
        this.completeUserInfoInterface = completeUserInfoInterface;
    }

    public static CompleteUserInfoDialogFragment newInstance(UserModel user, CompleteUserInfoInterface completeUserInfoInterface){
            final CompleteUserInfoDialogFragment dialog = new CompleteUserInfoDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            dialog.setArguments(bundle);
            dialog.setCompleteUserInfoInterface(completeUserInfoInterface);
            return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        user = (UserModel) getArguments().getSerializable("user");
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_update_user, null, false);
        binding.setCompleteUserInfoViewModel(new CompleteUserInfoViewModel(user));
        binding.setActivity(completeUserInfoInterface);

        com.example.traveltripapplication.util.dialog.DatePickerDialog datePickerDialog1
                = new com.example.traveltripapplication.util.dialog.DatePickerDialog(binding.datePickerBirthday, getContext());
        binding.datePickerBirthday.setOnClickListener(datePickerDialog1::openDatePicker);

        CompletableFuture<Bitmap> downLoadImageByUrl = CompletableFuture.supplyAsync(() -> {

            Bitmap image = null;
            try {
                InputStream in = new URL(user.getAvatar()).openStream();
                image = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return image;
        });

        downLoadImageByUrl.thenAcceptAsync(result -> {
            binding.shapeableImageView.setImageBitmap(result);
        });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(binding.getRoot());
        return builder.create();
    }



}
