package com.example.traveltripapplication.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.databinding.ItemResultSearchBinding;
import com.example.traveltripapplication.model.TourModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private final CopyOnWriteArrayList<TourModel> tourModels;
    private final SearchAdapterListener searchAdapterListener;

    public SearchAdapter(CopyOnWriteArrayList<TourModel> tourModels, SearchAdapterListener searchAdapterListener) {
        this.tourModels = tourModels;
        this.searchAdapterListener = searchAdapterListener;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemResultSearchBinding itemResultSearchBinding = ItemResultSearchBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemResultSearchBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
            TourModel tourModel = tourModels.get(position);
            if(tourModel != null) {
                Log.d("thumnail", "onBindViewHolder: " + tourModel.getThumbnail());
                Log.d("rating scrores", "onBindViewHolder: " + tourModel.getRatingTour());
                holder.mItemResultSearchBinding.tvResultTour.setText(tourModel.getTourTitle());
                String date = String.valueOf(tourModel.getTourDuration() + " Ngày");
                holder.mItemResultSearchBinding.tvDuration.setText(date);
                holder.mItemResultSearchBinding.ratingBar2.setRating((float) tourModel.getRatingTour());
                CompletableFuture<Bitmap> downLoadImageByUrl = CompletableFuture.supplyAsync(() -> {

                    Bitmap image = null;
                    try {
                        InputStream in = new URL(tourModel.getThumbnail()).openStream();
                        image = BitmapFactory.decodeStream(in);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return image;
                });
                downLoadImageByUrl.thenAcceptAsync(image -> {
                    holder.mItemResultSearchBinding.imgResult.setImageBitmap(image);
                });
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
