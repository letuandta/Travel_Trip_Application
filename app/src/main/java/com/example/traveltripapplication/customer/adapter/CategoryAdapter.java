package com.example.traveltripapplication.customer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.databinding.ItemCategoryBinding;
import com.example.traveltripapplication.model.CategoryModel;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final ArrayList<CategoryModel> categoryModels;
    private final CategoryAdapterListener listener;

    public CategoryAdapter(ArrayList<CategoryModel> categoryModels, CategoryAdapterListener listener) {
        this.categoryModels = categoryModels;
        this.listener = listener;
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
            holder.itemCategoryBinding.itemCate.setImageResource(categoryModel.getDrawableImage());

            holder.itemCategoryBinding.cvPlace.setOnClickListener(view -> {
                listener.onClickCategoryItem(categoryModel.getCateID());
            });
        }
        else return;
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final ItemCategoryBinding itemCategoryBinding;

        public ViewHolder(@NonNull ItemCategoryBinding itemCategoryBinding) {
            super(itemCategoryBinding.getRoot());
            this.itemCategoryBinding = itemCategoryBinding;
        }
    }

    public  interface CategoryAdapterListener{
        public void onClickCategoryItem(long cateId);
    }
}
