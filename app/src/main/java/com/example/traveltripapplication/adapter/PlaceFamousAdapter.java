package com.example.traveltripapplication.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.databinding.ItemPlaceBinding;
import com.example.traveltripapplication.model.TourModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlaceFamousAdapter extends RecyclerView.Adapter<PlaceFamousAdapter.ViewHolder> {

    private final CopyOnWriteArrayList<TourModel> tourModels;
    private PlaceFamounsAdapterListener listener;

    public PlaceFamousAdapter(CopyOnWriteArrayList<TourModel> tourModels, PlaceFamounsAdapterListener listener) {
        this.tourModels = tourModels;
        this.listener = listener;
        Log.d("tours", tourModels.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlaceBinding itemPlaceBinding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemPlaceBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TourModel tourModel = tourModels.get(position);
        if (tourModel != null){
            holder.itemPlaceBinding.tvTitle.setText(tourModel.getTourTitle());
            CharSequence subTitle = "Tour " + tourModel.getTourDuration() + " Ngày";
            holder.itemPlaceBinding.tvSubTitle.setText(subTitle);
            if(tourModel.getTourActive() != 0){
                holder.itemPlaceBinding.imageView.setImageResource(R.drawable.favorite_24);
            }
            else{
                holder.itemPlaceBinding.imageView.setImageResource(R.drawable.baseline_favorite_border_24);
            }
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

            downLoadImageByUrl.thenAcceptAsync(result -> {
                holder.itemPlaceBinding.imgPlace.setImageBitmap(result);
            });

            holder.itemPlaceBinding.cvPlace.setOnClickListener(view -> {
                listener.onPlaceClick(tourModel);
            });
        }
        else return;
    }

    @Override
    public int getItemCount() {
        return tourModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ItemPlaceBinding itemPlaceBinding;

        public ViewHolder(@NonNull ItemPlaceBinding itemPlaceBinding) {
            super(itemPlaceBinding.getRoot());
            this.itemPlaceBinding = itemPlaceBinding;
        }
    }

    public interface PlaceFamounsAdapterListener{
        void onPlaceClick(TourModel tourModel);
    }
}
