package com.example.traveltripapplication.customer.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.customer.OrderDetailActivity;
import com.example.traveltripapplication.databinding.ItemOrderListBinding;
import com.example.traveltripapplication.model.OrderModel;

import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    ArrayList<OrderModel> orderModels;

    public OrderListAdapter(ArrayList<OrderModel> orderModels) {
        this.orderModels = orderModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderListBinding binding = ItemOrderListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            OrderModel orderModel = orderModels.get(position);
            if(orderModel != null){
                holder.binding.tourTitle.setText(String.valueOf(orderModel.getTour_title()));
                holder.binding.tvCustomerName.setText(String.valueOf(orderModel.getCustomer_name()));
                holder.binding.tvOrderDate.setText(String.valueOf(orderModel.getOrder_date()));
                switch ((int) orderModel.getOrder_state_id()){
                    case 1:
                        holder.binding.tvOrderState.setText("Đang đợi thanh toán");
                        holder.binding.tvOrderState.setTextColor(ContextCompat.getColor(holder.binding.getRoot().getContext(),
                                R.color.shape_booking));
                        break;
                    case 2:
                        holder.binding.tvOrderState.setText("Đơn đã được huỷ");
                        holder.binding.tvOrderState.setTextColor(ContextCompat.getColor(holder.binding.getRoot().getContext(),
                                com.google.android.material.R.color.design_default_color_error));
                        break;
                    case 3:
                        holder.binding.tvOrderState.setText("Đã thanh toán");
                        holder.binding.tvOrderState.setTextColor(ContextCompat.getColor(holder.binding.getRoot().getContext(),
                                R.color.colorPrimary));
                        break;
                }
                holder.binding.cvItemOrder.setOnClickListener(v -> {
                    Intent intent = new Intent(v.getContext(), OrderDetailActivity.class);
                    intent.putExtra("order", orderModel);
                    intent.putExtra("position", position);
                    v.getContext().startActivity(intent);
                });
            }
    }

    @Override
    public int getItemCount() {
        if(orderModels != null) return orderModels.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ItemOrderListBinding binding;

        public ViewHolder(@NonNull ItemOrderListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void updateAllData(ArrayList<OrderModel> orderModels){
        this.orderModels.clear();
        this.orderModels.addAll(orderModels);
        notifyDataSetChanged();
    }

    public void updateSingleData(OrderModel orderModel, int position){
        if(position != -1)
        {
            this.orderModels.set(position, orderModel);
            notifyItemChanged(position);
        }
    }
}
