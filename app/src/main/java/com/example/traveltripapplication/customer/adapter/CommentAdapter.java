package com.example.traveltripapplication.customer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.databinding.ItemCommentBinding;
import com.example.traveltripapplication.model.RatingModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    ArrayList<RatingModel> ratingModels;

    public CommentAdapter(ArrayList<RatingModel> ratingModels) {
        this.ratingModels = ratingModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCommentBinding binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RatingModel ratingModel = ratingModels.get(position);
        if(ratingModel != null){
            holder.binding.userFullName.setText(String.valueOf(ratingModel.getUserModel().getFull_name()));
            holder.binding.ratingScores.setText(String.valueOf(ratingModel.getScores()));
            holder.binding.ratingDate.setText(String.valueOf(ratingModel.getCreatedDate()));
            holder.binding.ratingMessage.setText(String.valueOf(ratingModel.getMessage()));

            Picasso.get().load(ratingModel.getUserModel().getAvatar()).into(holder.binding.userAvatar);
        }

    }

    @Override
    public int getItemCount() {
        if(ratingModels != null) return ratingModels.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ItemCommentBinding binding;

        public ViewHolder(@NonNull ItemCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void loadMoreData(ArrayList<RatingModel> ratingModels){
        this.ratingModels.addAll(ratingModels);
        notifyItemRangeChanged(this.ratingModels.size() - 2, ratingModels.size());
    }

    public void addSingleData(RatingModel ratingModel){
        this.ratingModels.add(0, ratingModel);
        notifyItemInserted(0);
    }
}
