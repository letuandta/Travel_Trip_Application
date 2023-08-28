package com.example.traveltripapplication.customer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.databinding.ItemOrderDetailTicketBinding;
import com.example.traveltripapplication.model.TourTicketModel;

import java.util.ArrayList;

public class OrderTicketListAdapter extends RecyclerView.Adapter<OrderTicketListAdapter.ViewHolder> {

    ArrayList<TourTicketModel> tourTicketModels;

    public OrderTicketListAdapter(ArrayList<TourTicketModel> tourTicketModels) {
        this.tourTicketModels = tourTicketModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderDetailTicketBinding binding = ItemOrderDetailTicketBinding.inflate(LayoutInflater.from(parent.getContext()), null, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TourTicketModel tourTicketModel = tourTicketModels.get(position);
        if(tourTicketModel != null){
            holder.binding.tvTitle.setText(String.valueOf(tourTicketModel.getTitle()));
            holder.binding.tvQuantityTicket.setText(String.valueOf(tourTicketModel.getQuantity()));
            int total = tourTicketModel.getQuantity() * Integer.parseInt(tourTicketModel.getPrice());
            holder.binding.tvTotalPrice.setText(String.valueOf(total));
        }
    }

    @Override
    public int getItemCount() {
        if(tourTicketModels != null) return tourTicketModels.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ItemOrderDetailTicketBinding binding;

        public ViewHolder(@NonNull ItemOrderDetailTicketBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
