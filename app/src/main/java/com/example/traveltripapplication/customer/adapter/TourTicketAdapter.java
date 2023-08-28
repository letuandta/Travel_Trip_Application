package com.example.traveltripapplication.customer.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.databinding.ItemDetailTicketBinding;
import com.example.traveltripapplication.model.TourTicketModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TourTicketAdapter extends RecyclerView.Adapter<TourTicketAdapter.ViewHolder> {

    List<TourTicketModel> tourTicketModels;
    Map<Long, Integer> orderTicket = new HashMap<>();
    TourTicketAdapterListener listener;

    public TourTicketAdapter(List<TourTicketModel> tourTicketModels, TourTicketAdapterListener listener) {
        this.tourTicketModels = tourTicketModels;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDetailTicketBinding binding = ItemDetailTicketBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            TourTicketModel tourTicketModel = tourTicketModels.get(position);

            //Set data
            holder.binding.tvTitle.setText(String.valueOf(tourTicketModel.getTitle()));
            holder.binding.tvDescription.setText(String.valueOf(tourTicketModel.getDescription()));
            holder.binding.tvTourDate.setText(String.valueOf(tourTicketModel.getTour_date()));
            holder.binding.tvRemaining.setText(String.valueOf(tourTicketModel.getRemaining()));
            holder.binding.tvAmount.setText(String.valueOf(tourTicketModel.getAmount()));
            holder.binding.tvPrice.setText(String.valueOf(tourTicketModel.getPrice()));

            //register event
            holder.binding.checkbox.setOnCheckedChangeListener((compoundButton, check) -> {
                boolean contain = orderTicket.containsKey(tourTicketModel.getId());
                int quantity = Integer.parseInt(String.valueOf(holder.binding.edtQuantity.getText()));
                if(check){
                    if(!contain){
                        orderTicket.put(tourTicketModel.getId(), quantity);
                    }
                }
                else {
                    if(contain) orderTicket.remove(tourTicketModel.getId());
                }
            });
            holder.binding.edtQuantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    return;
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    return;
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(!holder.binding.edtQuantity.getText().toString().equals(""))
                        orderTicket.put(tourTicketModel.getId(), Integer.parseInt(String.valueOf(holder.binding.edtQuantity.getText())));
                }
            });

        }catch (Exception e)
        {
            Log.e("tour ticket adapter", "onBindViewHolder: " + e.getMessage() );
        }

    }

    @Override
    public int getItemCount() {
        if(tourTicketModels != null) return tourTicketModels.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ItemDetailTicketBinding binding;

        public ViewHolder(@NonNull ItemDetailTicketBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public Map<Long, Integer> getOrderTicket() {
        return orderTicket;
    }

    public interface TourTicketAdapterListener{}
}
