package com.example.traveltripapplication.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.databinding.ItemCategoryBinding;
import com.example.traveltripapplication.databinding.ItemPlaceBinding;
import com.example.traveltripapplication.model.CategoryModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final ArrayList<CategoryModel> categoryModels;

    public CategoryAdapter(ArrayList<CategoryModel> categoryModels) {
        this.categoryModels = categoryModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding itemCategoryBinding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemCategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryModel categoryModel = categoryModels.get(position);
        if (categoryModel != null) {
            holder.itemCategoryBinding.tvCate.setText(categoryModel.getCateName());
//            CompletableFuture<Bitmap> downLoadImageByUrl = CompletableFuture.supplyAsync(() -> {
//                Bitmap image = null;
//                try {
//                    InputStream in = new URL(categoryModel.getImageUrl()).openStream();
//                    image = BitmapFactory.decodeStream(in);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                return image;
//            });
//            downLoadImageByUrl.thenAcceptAsync(result -> {
//                holder.itemCategoryBinding.itemCate.setImageBitmap(result);
//            });
        }
        else return;
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ItemCategoryBinding itemCategoryBinding;

        public ViewHolder(@NonNull ItemCategoryBinding itemCategoryBinding) {
            super(itemCategoryBinding.getRoot());
            this.itemCategoryBinding = itemCategoryBinding;
        }
    }
}
