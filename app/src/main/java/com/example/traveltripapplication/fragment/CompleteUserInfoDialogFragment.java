package com.example.traveltripapplication.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.databinding.FragmentUpdateUserBinding;
import com.example.traveltripapplication.interfaceviewmodel.CompleteUserInfoInterface;
import com.example.traveltripapplication.model.UserModel;
import com.example.traveltripapplication.viewmodel.CompleteUserInfoViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

public class CompleteUserInfoDialogFragment extends DialogFragment{

    private UserModel user;
    private  FragmentUpdateUserBinding binding;

    private static CompleteUserInfoDialogFragment INSTANCE;
    
    private DatePickerDialog datePickerDialog;
    private  CompleteUserInfoInterface completeUserInfoInterface;

    public void setCompleteUserInfoInterface(CompleteUserInfoInterface completeUserInfoInterface) {
        this.completeUserInfoInterface = completeUserInfoInterface;
    }

    public static CompleteUserInfoDialogFragment newInstance(UserModel user, CompleteUserInfoInterface completeUserInfoInterface){
            final CompleteUserInfoDialogFragment dialog = new CompleteUserInfoDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("USER", user);
            dialog.setArguments(bundle);
            dialog.setCompleteUserInfoInterface(completeUserInfoInterface);
            return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        user = (UserModel) getArguments().getParcelable("USER");
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_update_user, null, false);
        binding.setCompleteUserInfoViewModel(new CompleteUserInfoViewModel(user));
        binding.setActivity(completeUserInfoInterface);
        
        initDatePicker();
        binding.datePickerBirthday.setOnClickListener(this::openDatePicker);

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

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                binding.datePickerBirthday.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(getContext(), style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    public void openDatePicker(View view){
        datePickerDialog.show();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(binding.getRoot());
        return builder.create();
    }



}
