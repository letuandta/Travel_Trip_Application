package com.example.traveltripapplication.customer.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.traveltripapplication.customer.adapter.CommentAdapter;
import com.example.traveltripapplication.data.repository.TourRepository;
import com.example.traveltripapplication.databinding.FragmentDetailCommentBinding;
import com.example.traveltripapplication.model.RatingModel;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CommentFragment extends Fragment implements AddCommentFragment.AddCommentListener {

    public  static boolean checkAddComment = false;
    public static RatingModel singleRatingData = new RatingModel();

    FragmentDetailCommentBinding binding;

    public long getTourId() {
        return tourId;
    }

    long tourId;
    int currentPage = 1;
    int countComment = 0;

    public CommentFragment(long tourId) {
        this.tourId = tourId;
    }

    public CommentFragment() {
    }

    CommentAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailCommentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<RatingModel> ratingModels = getComment();
        if(ratingModels.size() > 0){
            adapter = new CommentAdapter(ratingModels);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

            binding.rcvComment.setAdapter(adapter);
            binding.rcvComment.setLayoutManager(layoutManager);
            binding.seeMore.setText("Xem thêm (" + currentPage * 2 + "/" + countComment + ")");

            binding.seeMore.setOnClickListener(v -> {
                seeMoreClick();
            });

            binding.addComment.setOnClickListener(v -> {
                AddCommentFragment addCommentFragment = AddCommentFragment.newInstance(tourId, this);
                addCommentFragment.show(requireActivity().getSupportFragmentManager(), "add_comment");
            });
        }
    }

    public ArrayList<RatingModel> getComment(){
        ArrayList<RatingModel> ratingModels;
        try {
            this.countComment = TourRepository.getCommentCountByTourId(tourId).get();
            ratingModels = TourRepository.getRatingByTourId(tourId, currentPage).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Log.d("comment", "getComment: " + ratingModels.size());
        return ratingModels;
    }

    public void seeMoreClick(){
        int check = currentPage * 2 - countComment;
        Log.d("seeMore", "seeMoreClick: " + check);
        if(check > 0) return;
        else {
            currentPage = currentPage + 1;
            try {
                ArrayList<RatingModel> ratingModels = TourRepository.getRatingByTourId(tourId, currentPage).get();
                adapter.loadMoreData(ratingModels);
                binding.seeMore.setText("Xem thêm (" + currentPage * 2 + "/" + countComment + ")");
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void addSuccess(RatingModel ratingModel) {
            adapter.addSingleData(ratingModel);
            binding.rcvComment.smoothScrollToPosition(0);
        }
}
