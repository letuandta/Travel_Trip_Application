package com.example.traveltripapplication.customer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.databinding.ItemCategoryBinding;
<<<<<<< HEAD:app/src/main/java/com/example/traveltripapplication/adapter/CategoryAdapter.java
import com.example.traveltripapplication.databinding.ItemListCateBinding;
=======
>>>>>>> 4c5b788a880e0664f1dcc4edfb849e6e44acdf31:app/src/main/java/com/example/traveltripapplication/customer/adapter/CategoryAdapter.java
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
        ItemListCateBinding itemListCateBinding = ItemListCateBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemCategoryBinding, itemListCateBinding);
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

            holder.itemListCateBinding.tvCateCode.setText(categoryModel.getCateCode());
            holder.itemListCateBinding.tvCateName.setText(categoryModel.getCateName());
        }
        else return;
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final ItemCategoryBinding itemCategoryBinding;
        private  final ItemListCateBinding itemListCateBinding;

        public ViewHolder(@NonNull ItemCategoryBinding itemCategoryBinding, ItemListCateBinding itemListCateBinding) {
            super(itemCategoryBinding.getRoot());
            this.itemCategoryBinding = itemCategoryBinding;
            this.itemListCateBinding = itemListCateBinding;
        }


    }

    public  interface CategoryAdapterListener{
        public void onClickCategoryItem(long cateId);
    }
}
