package com.example.traveltripapplication.customer.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.customer.MainPageActivity;
import com.example.traveltripapplication.data.repository.OrderRepository;
import com.example.traveltripapplication.databinding.FragmentOrderCompleteInfoBinding;
import com.example.traveltripapplication.model.OrderModel;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class CompleteInfoCustomerDialogFragment extends DialogFragment {

    private long userId = MainPageActivity.userModel.get_ID();
    private long tourId;

    private Map<Long, Integer> orderTicket;

    public void setOrderTicket(Map<Long, Integer> orderTicket) {
        this.orderTicket = orderTicket;
    }

    public void setTourId(long tourId) {
        this.tourId = tourId;
    }

    private FragmentOrderCompleteInfoBinding binding;

    public static CompleteInfoCustomerDialogFragment newInstance(long tourId, Map<Long, Integer> orderTicket){
        CompleteInfoCustomerDialogFragment dialog = new CompleteInfoCustomerDialogFragment();
        dialog.setOrderTicket(orderTicket);
        dialog.setTourId(tourId);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_order_complete_info, null, false);
        binding.btnConfirm.setOnClickListener(v -> {
            OrderModel orderModel = new OrderModel();
            orderModel.setTour_id(this.tourId);
            orderModel.setUser_id(this.userId);
            orderModel.setAddress(String.valueOf(binding.address.getText()));
            orderModel.setCustomer_name(String.valueOf(binding.hoten.getText()));
            orderModel.setPhone(String.valueOf(binding.phone.getText()));
            orderModel.setOrder_tickets(orderTicket);
            CompletableFuture<Long> createOrder = OrderRepository.createOrder(orderModel);
            createOrder.thenAcceptAsync(result -> {
                if(result < 0) Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
                else this.dismiss();
            });
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Toast.makeText(getContext(), "Dat hang thanh cong!!", Toast.LENGTH_LONG).show();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(binding.getRoot());
        return builder.create();
    }
}
