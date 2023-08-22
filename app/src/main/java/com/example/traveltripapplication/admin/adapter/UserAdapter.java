package com.example.traveltripapplication.admin.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.admin.AdminActivity;
import com.example.traveltripapplication.admin.UpdateUserAdminActivity;
import com.example.traveltripapplication.data.database.DatabaseHelper;
import com.example.traveltripapplication.databinding.ItemListUserBinding;
import com.example.traveltripapplication.model.UserModel;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private  final ArrayList<UserModel> userModels;

    public UserAdapter(ArrayList<UserModel> userModels) {
        this.userModels = userModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListUserBinding itemListUserBinding = ItemListUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemListUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel userModel = userModels.get(position);
        if (userModel != null) {
            holder.itemListUserBinding.tvFullname.setText(userModel.getFull_name());
            holder.itemListUserBinding.tvRole.setText((userModel.getIs_super_user()==1 ? "admin" : "customer"));
            holder.itemListUserBinding.tvEmail.setText((userModel.getEmail()));
            holder.itemListUserBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), UpdateUserAdminActivity.class);
                    intent.putExtra("user", userModel);
                    view.getContext().startActivity(intent);
                }
            });
            holder.itemListUserBinding.btnRemove.setOnClickListener(view -> {
                DatabaseHelper.mContactsHelper().delete(userModel.getContacts_id());
                DatabaseHelper.mUserHelper().delete(userModel.get_ID());
                if (userModels.remove(userModel))
                    this.notifyItemRemoved(position);
            });
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

    public void onLoadData(ArrayList<UserModel> userModels) {
        this.userModels.clear();
        this.userModels.addAll(userModels);
        this.notifyDataSetChanged();
    }

    public void loadDataLastPosition(UserModel userModel){
        this.userModels.add(userModel);
        this.notifyItemInserted(userModels.size() - 1);
    }

    public void updateData(UserModel userModel, int position){
        if(position != -1){
        this.userModels.set(position, userModel);
        this.notifyItemChanged(position);
        }
    }

}
