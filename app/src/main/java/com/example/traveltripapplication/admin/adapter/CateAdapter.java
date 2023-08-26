package com.example.traveltripapplication.admin.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.admin.UpdateCateAdminActivity;
import com.example.traveltripapplication.data.database.DatabaseHelper;
import com.example.traveltripapplication.databinding.ItemListCateBinding;
import com.example.traveltripapplication.model.CategoryModel;
import com.example.traveltripapplication.model.UserModel;

import java.util.ArrayList;

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.ViewHolder> {
    private final ArrayList<CategoryModel> categoryModels;

    public CateAdapter(ArrayList<CategoryModel> categoryModels) {this.categoryModels = categoryModels;}

    @NonNull
    @Override
    public CateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListCateBinding itemListCateBinding = ItemListCateBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemListCateBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CateAdapter.ViewHolder holder, int position) {
        CategoryModel categoryModel = categoryModels.get(position);
        if (categoryModel != null) {
            holder.itemListCateBinding.tvCateCode.setText(categoryModel.getCateCode());
            holder.itemListCateBinding.tvCateName.setText(categoryModel.getCateName());
            holder.itemListCateBinding.btnChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), UpdateCateAdminActivity.class);
                    intent.putExtra("cate", categoryModel);
                    intent.putExtra("position", holder.getLayoutPosition());
                    view.getContext().startActivity(intent);
                }
            });
            holder.itemListCateBinding.btnRemove.setOnClickListener(view -> {
                DatabaseHelper.mUserHelper().delete(categoryModel.getCateID());
                if (categoryModels.remove(categoryModel))
                    this.notifyItemRemoved(position);
            });
        }
    }

    @Override
    public int getItemCount() {return categoryModels.size();}

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemListCateBinding itemListCateBinding;

        public ViewHolder(@NonNull ItemListCateBinding itemListCateBinding) {
            super(itemListCateBinding.getRoot());
            this.itemListCateBinding = itemListCateBinding;
        }
    }

    public void loadDataLastPosition(CategoryModel categoryModel) {
        this.categoryModels.add(categoryModel);
        this.notifyItemInserted(categoryModels.size() -1);
    }

    public void updateData(CategoryModel categoryModel, int position){
        if(position != -1){
            this.categoryModels.set(position, categoryModel);
            this.notifyItemChanged(position);
        }
    }
}
