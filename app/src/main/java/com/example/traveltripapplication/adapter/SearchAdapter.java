package com.example.traveltripapplication.adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.databinding.ItemResultSearchBinding;
import com.example.traveltripapplication.model.TourModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final ArrayList<TourModel> tourModels = new ArrayList<>();
    private final SearchAdapterListener searchAdapterListener;

    public SearchAdapter( SearchAdapterListener searchAdapterListener) {
        this.searchAdapterListener = searchAdapterListener;
    }

    public void updateData(ArrayList<TourModel> tourModels) {
        this.tourModels.clear();
        this.tourModels.addAll(tourModels);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemResultSearchBinding itemResultSearchBinding = ItemResultSearchBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemResultSearchBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            TourModel tourModel = tourModels.get(position);
            if(tourModel != null) {
                Log.d("thumnail", "onBindViewHolder: " + tourModel.getThumbnail());
                Log.d("rating scrores", "onBindViewHolder: " + tourModel.getRatingTour());
                holder.mItemResultSearchBinding.tvResultTour.setText(tourModel.getTourTitle());
                String date = String.valueOf(tourModel.getTourDuration() + " Ngày");
                holder.mItemResultSearchBinding.tvDuration.setText(date);
                holder.mItemResultSearchBinding.ratingBar2.setRating((float) tourModel.getRatingTour());
                Picasso.get().load(tourModel.getThumbnail()).into(holder.mItemResultSearchBinding.imgResult);
                holder.mItemResultSearchBinding.cvResultSearch.setOnClickListener(view -> {
                    searchAdapterListener.onClickItem(tourModel);
                });
            }
            else return;
    }

    @Override
    public int getItemCount() {
        if(tourModels != null)
            return tourModels.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ItemResultSearchBinding mItemResultSearchBinding;

        public ViewHolder(@NonNull ItemResultSearchBinding itemResultSearchBinding) {
            super(itemResultSearchBinding.getRoot());
            this.mItemResultSearchBinding = itemResultSearchBinding;
        }
    }

    public interface SearchAdapterListener{
        public void onClickItem(TourModel tourModel);
    }
}
