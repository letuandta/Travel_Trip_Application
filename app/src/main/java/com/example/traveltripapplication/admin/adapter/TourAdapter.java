package com.example.traveltripapplication.admin.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.databinding.ItemListTourBinding;
import com.example.traveltripapplication.model.TourModel;

import java.util.ArrayList;


public class TourAdapter extends RecyclerView.Adapter<TourAdapter.ViewHolder> {
    private final ArrayList<TourModel> tourModels;

    public TourAdapter(ArrayList<TourModel> tourModels) {
        this.tourModels = tourModels;
    }
    @NonNull
    @Override
    public TourAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListTourBinding itemListTourBinding = ItemListTourBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemListTourBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TourAdapter.ViewHolder holder, int position) {
        TourModel tourModel = tourModels.get(position);
        if (tourModel != null) {
            holder.itemListTourBinding.tvTourCode.setText(tourModel.getTourCode());
            holder.itemListTourBinding.tvTourTitle.setText(tourModel.getTourTitle());
        }

    }

    @Override
    public int getItemCount() { return tourModels.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemListTourBinding itemListTourBinding;

        public ViewHolder(@NonNull ItemListTourBinding itemListTourBinding) {
            super(itemListTourBinding.getRoot());
            this.itemListTourBinding = itemListTourBinding;
        }
    }
}

