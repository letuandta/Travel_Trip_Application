package com.example.traveltripapplication.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.databinding.ItemCategoryBinding;
import com.example.traveltripapplication.databinding.ItemListUserBinding;
import com.example.traveltripapplication.model.CategoryModel;
import com.example.traveltripapplication.model.UserModel;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private  final CopyOnWriteArrayList<UserModel> userModels;

    public UserAdapter(CopyOnWriteArrayList<UserModel> userModels) {
        this.userModels = userModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListUserBinding itemListUserBinding = ItemListUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemListUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        UserModel userModel = userModels.get(position);
        if (userModel != null) {
            holder.itemListUserBinding.tvFullname.setText(userModel.getFull_name());
            holder.itemListUserBinding.tvRole.setText((userModel.getIs_super_user()==1 ? "admin" : "customer"));
        }
        else return;
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemListUserBinding itemListUserBinding;

        public ViewHolder(@NonNull ItemListUserBinding itemListUserBinding) {
            super(itemListUserBinding.getRoot());
            this.itemListUserBinding = itemListUserBinding;
        }
    }

    public void onLoadData(CopyOnWriteArrayList<UserModel> userModels) {
        this.userModels.clear();
        this.userModels.addAll(userModels);
        this.notifyDataSetChanged();
    }
}
