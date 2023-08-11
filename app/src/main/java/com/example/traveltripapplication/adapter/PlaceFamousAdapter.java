package com.example.traveltripapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.databinding.ItemPlaceBinding;
import com.example.traveltripapplication.model.PlaceFamousModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class PlaceFamousAdapter extends RecyclerView.Adapter<PlaceFamousAdapter.ViewHolder> {

    private final ArrayList<PlaceFamousModel> placeFamousModels;

    public PlaceFamousAdapter(ArrayList<PlaceFamousModel> placeFamousModels) {
        this.placeFamousModels = placeFamousModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPlaceBinding itemPlaceBinding = ItemPlaceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemPlaceBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlaceFamousModel placeFamousModel = placeFamousModels.get(position);
        if (placeFamousModel != null){
            holder.itemPlaceBinding.tvTitle.setText(placeFamousModel.getPlaceName());
            holder.itemPlaceBinding.tvSubTitle.setText(placeFamousModel.getCountry());
            if(placeFamousModel.getIsFavorite() != 0){
                holder.itemPlaceBinding.imageView.setImageResource(R.drawable.favorite_24);
            }
            else{
                holder.itemPlaceBinding.imageView.setImageResource(R.drawable.baseline_favorite_border_24);
            }
            CompletableFuture<Bitmap> downLoadImageByUrl = CompletableFuture.supplyAsync(() -> {

                Bitmap image = null;
                try {
                    InputStream in = new URL(placeFamousModel.getImageUrl()).openStream();
                    image = BitmapFactory.decodeStream(in);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return image;
            });

            downLoadImageByUrl.thenAcceptAsync(result -> {
                holder.itemPlaceBinding.imgPlace.setImageBitmap(result);
            });
        }
        else return;
    }

    @Override
    public int getItemCount() {
        return placeFamousModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ItemPlaceBinding itemPlaceBinding;

        public ViewHolder(@NonNull ItemPlaceBinding itemPlaceBinding) {
            super(itemPlaceBinding.getRoot());
            this.itemPlaceBinding = itemPlaceBinding;
        }
    }
}
