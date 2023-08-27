package com.example.traveltripapplication.admin.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.admin.UpdateTourAdminActivity;
import com.example.traveltripapplication.admin.UpdateUserAdminActivity;
import com.example.traveltripapplication.data.database.DatabaseHelper;
import com.example.traveltripapplication.databinding.ItemListTourBinding;
import com.example.traveltripapplication.model.CategoryModel;
import com.example.traveltripapplication.model.TourModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class TourAdapter extends RecyclerView.Adapter<TourAdapter.ViewHolder> {
    private final ArrayList<TourModel> tourModels;

    public TourAdapter(ArrayList<TourModel> tourModels) {
        this.tourModels = tourModels;
    }
    @NonNull
    @Override
    public TourAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListTourBinding itemListTourBinding = ItemListTourBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemListTourBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TourAdapter.ViewHolder holder, int position) {
        TourModel tourModel = tourModels.get(position);
        if (tourModel != null) {
            Log.d("thumnail", "onBindViewHolder: " + tourModel.getThumbnail());
            holder.itemListTourBinding.tvTourCode.setText(tourModel.getTourCode());
            holder.itemListTourBinding.tvTourTitle.setText(tourModel.getTourTitle());
            holder.itemListTourBinding.tvTourLocation.setText(tourModel.getTourLocation());
            holder.itemListTourBinding.tvTourInformation.setText(tourModel.getMoreInfo());
            Picasso.get().load(tourModel.getThumbnail()).into(holder.itemListTourBinding.imgThumbnail);
            holder.itemListTourBinding.tvTourDuration.setText(String.valueOf(tourModel.getTourDuration()));

            holder.itemListTourBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), UpdateTourAdminActivity.class);
                    intent.putExtra("tour", tourModel);
                    intent.putExtra("position",  holder.getLayoutPosition());
                    v.getContext().startActivity(intent);
                }
            });
            holder.itemListTourBinding.btnRemove.setOnClickListener(view -> {
                DatabaseHelper.mTourHelper().delete(tourModel.getTourID());
                if (tourModels.remove(tourModel))
                    this.notifyItemRemoved(position);
            });
        }
    }

    @Override
    public int getItemCount() { return tourModels.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemListTourBinding itemListTourBinding;

        public ViewHolder(@NonNull ItemListTourBinding itemListTourBinding) {
            super(itemListTourBinding.getRoot());
            this.itemListTourBinding = itemListTourBinding;
        }
    }
    public void loadDataLastPosition(TourModel tourModel) {
        this.tourModels.add(tourModel);
        this.notifyItemInserted(tourModels.size() -1);
    }

    public void updateData(TourModel tourModel, int position){
        if(position != -1){
            this.tourModels.set(position, tourModel);
            this.notifyItemChanged(position);
        }
    }
}

