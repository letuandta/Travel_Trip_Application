package com.example.traveltripapplication.customer.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.customer.MainPageActivity;
import com.example.traveltripapplication.data.repository.TourRepository;
import com.example.traveltripapplication.databinding.FragmentAddCommentBinding;
import com.example.traveltripapplication.model.RatingModel;

import java.util.concurrent.ExecutionException;

public class AddCommentFragment extends DialogFragment {

    FragmentAddCommentBinding binding;
    long tourId;

    AddCommentListener listener;
    public AddCommentFragment(long tourId, AddCommentListener listener) {
        this.tourId = tourId;
        this.listener = listener;
    }

    RatingModel ratingModel;

    public static AddCommentFragment newInstance(long tourId, AddCommentListener listener){
        AddCommentFragment dialog = new AddCommentFragment(tourId, listener);
        return dialog;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_add_comment, null, false);
        super.onCreate(savedInstanceState);

        binding.btnAdd.setOnClickListener(v -> {
            ratingModel = new RatingModel();
            ratingModel.setUserId(MainPageActivity.userModel.get_ID());
            ratingModel.setTourId(tourId);
            ratingModel.setMessage(binding.edtMessage.getText().toString());
            ratingModel.setScores(binding.rbScores.getRating());
            ratingModel.setUserModel(MainPageActivity.userModel);

            try {
                long id = TourRepository.createRating(ratingModel).get();
                if(id > 0) {
                    this.dismiss();
                }
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(binding.getRoot());
        return builder.create();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        Log.d("add commetm dialog", "onDismiss: ");
        listener.addSuccess(ratingModel);
        super.onDismiss(dialog);
    }

    public interface AddCommentListener{
        public void addSuccess(RatingModel ratingModel);
    }
}
