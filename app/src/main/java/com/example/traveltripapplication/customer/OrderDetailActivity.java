package com.example.traveltripapplication.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.customer.adapter.OrderListAdapter;
import com.example.traveltripapplication.customer.adapter.OrderTicketListAdapter;
import com.example.traveltripapplication.customer.fragment.OrderListFragment;
import com.example.traveltripapplication.data.repository.OrderRepository;
import com.example.traveltripapplication.databinding.ActivityOrderDetailBinding;
import com.example.traveltripapplication.model.OrderModel;
import com.example.traveltripapplication.model.TourTicketModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OrderDetailActivity extends AppCompatActivity {

    ActivityOrderDetailBinding binding;
    OrderModel orderModel;
    int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_order_detail, null, false);
        setContentView(binding.getRoot());
        orderModel = (OrderModel) getIntent().getExtras().getSerializable("order");
        position = getIntent().getExtras().getInt("position");
        assert orderModel != null;
        binding.imgArrowOrder.setOnClickListener(v -> this.onBackPressed());
        initData();

        binding.btnCancel.setOnClickListener(v -> {
            cancelOrder();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void initData(){
        binding.tvCustomerName.setText(String.valueOf(orderModel.getCustomer_name()));
        binding.tvCustomerPhone.setText(String.valueOf(orderModel.getPhone()));
        binding.tvTourTitle.setText(String.valueOf(orderModel.getTour_title()));
        binding.tvOrderDate.setText(String.valueOf(orderModel.getOrder_date()));
        binding.tvCustomerAddress.setText(String.valueOf(orderModel.getAddress()));

        switch ((int) orderModel.getOrder_state_id()){
            case 1:
                binding.tvOrderState.setText("Đang đợi thanh toán");
                binding.tvOrderState.setTextColor(ContextCompat.getColor(this,
                        R.color.shape_booking));
                break;
            case 2:
                binding.tvOrderState.setText("Đơn đã được huỷ");
                binding.tvOrderState.setTextColor(ContextCompat.getColor(this,
                        com.google.android.material.R.color.design_default_color_error));
                break;
            case 3:
                binding.tvOrderState.setText("Đã thanh toán");
                binding.tvOrderState.setTextColor(ContextCompat.getColor(this,
                        R.color.colorPrimary));
                break;
        }

        ArrayList<TourTicketModel> list = getTicket();
        OrderTicketListAdapter adapter = new OrderTicketListAdapter(list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rcvTicket.setAdapter(adapter);
        binding.rcvTicket.setLayoutManager(layoutManager);
    }

    public ArrayList<TourTicketModel> getTicket(){
        ArrayList<TourTicketModel> tourTicketModels;
        CompletableFuture<ArrayList<TourTicketModel>> future = OrderRepository.getTicketByOrderId(orderModel.get_id());
        try {
            tourTicketModels = new ArrayList<>(future.get());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return tourTicketModels;
    }

    public void cancelOrder(){
        int check = -1;
        try {
            check = OrderRepository.cancelOrder(orderModel.get_id()).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(check > 0){
            Toast.makeText(this, "cancel success", Toast.LENGTH_SHORT).show();
            binding.tvOrderState.setText("Đơn đã được huỷ");
            binding.tvOrderState.setTextColor(ContextCompat.getColor(this,
                    com.google.android.material.R.color.design_default_color_error));
            OrderListFragment.checkUpdateData = true;
            orderModel.setOrder_state_id(2);
            OrderListFragment.singleOrderModel = orderModel;
            OrderListFragment.singlePosition = position;
        }
    }
}
