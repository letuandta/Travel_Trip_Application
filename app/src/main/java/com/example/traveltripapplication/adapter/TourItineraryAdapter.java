package com.example.traveltripapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.databinding.ItemDetailItineraryBinding;
import com.example.traveltripapplication.model.TourItineraryModel;

import java.util.ArrayList;

public class TourItineraryAdapter extends RecyclerView.Adapter<TourItineraryAdapter.ViewHolder> {

    final ArrayList<TourItineraryModel> tourItineraryModels;

    public TourItineraryAdapter(ArrayList<TourItineraryModel> tourItineraryModels) {
        this.tourItineraryModels = tourItineraryModels;
    }

    public void updateData(ArrayList<TourItineraryModel> tourItineraryModels){
        this.tourItineraryModels.clear();
        this.tourItineraryModels.addAll(tourItineraryModels);
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDetailItineraryBinding binding = ItemDetailItineraryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TourItineraryModel tourItineraryModel = tourItineraryModels.get(position);
        if(tourItineraryModel != null){
            holder.binding.tvDay.setText(String.valueOf("Ngày" + tourItineraryModel.getDay()));
            holder.binding.tvContent.setText(String.valueOf(tourItineraryModel.getContentRaw()));
        }
    }

    @Override
    public int getItemCount() {
        if (tourItineraryModels != null)
            return tourItineraryModels.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ItemDetailItineraryBinding binding;

        public ViewHolder(@NonNull ItemDetailItineraryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
