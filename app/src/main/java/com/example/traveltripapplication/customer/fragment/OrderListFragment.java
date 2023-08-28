package com.example.traveltripapplication.customer.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.traveltripapplication.customer.MainPageActivity;
import com.example.traveltripapplication.customer.adapter.OrderListAdapter;
import com.example.traveltripapplication.data.repository.OrderRepository;
import com.example.traveltripapplication.databinding.FragmentOrderListBinding;
import com.example.traveltripapplication.model.OrderModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OrderListFragment extends Fragment {

    FragmentOrderListBinding binding;

    OrderListAdapter adapter;

    public static boolean checkUpdateData = false;
    public static OrderModel singleOrderModel = new OrderModel();
    public static int singlePosition = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<OrderModel> orderModels = getOrderByUserId();
        Log.d("order size", "" + getOrderByUserId().size());
        if(orderModels.size() > 0){
            binding.tvEmptyOrder.setVisibility(View.GONE);
            adapter = new OrderListAdapter(orderModels);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

            binding.rcvListOrder.setLayoutManager(layoutManager);
            binding.rcvListOrder.setAdapter(adapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(OrderListFragment.checkUpdateData){
            adapter.updateSingleData(OrderListFragment.singleOrderModel, OrderListFragment.singlePosition);
            OrderListFragment.checkUpdateData = false;
        }
    }

    public ArrayList<OrderModel> getOrderByUserId(){
        long userId = MainPageActivity.userModel.get_ID();
        ArrayList<OrderModel> orderModels = new ArrayList<>();
        CompletableFuture<ArrayList<OrderModel>> future = OrderRepository.getOrderByUserId(userId);
        try {
            orderModels.addAll(future.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Log.d("order size", "" + orderModels.size());

        return orderModels;
    }
}
