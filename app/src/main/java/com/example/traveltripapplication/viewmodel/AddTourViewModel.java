package com.example.traveltripapplication.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.traveltripapplication.admin.fragment.AddTourFragment;
import com.example.traveltripapplication.data.repository.TourRepository;
import com.example.traveltripapplication.model.TourModel;

import java.util.concurrent.CompletableFuture;

public class AddTourViewModel extends BaseObservable {
    public final ObservableField<String> duration = new ObservableField<>("1");

    private TourModel tourModel = new TourModel();
    private addTourViewModelListener listener;

    public AddTourViewModel(addTourViewModelListener listener) {
        this.listener = listener;
    }

    @Bindable
    public TourModel getTourModel() {
        return tourModel;
    }

    public void setTourModel(TourModel tourModel) {
        this.tourModel = tourModel;
    }

    public void onClickButtonAdd() {
        tourModel.setThumbnail("https://i.pinimg.com/564x/ef/69/c5/ef69c56e319d6db337527303f3501ee1.jpg");
        tourModel.setTourDuration(Integer.parseInt(String.valueOf(duration.get())));
        tourModel.setTourActive(1);
        CompletableFuture<Long> addTourFuture = TourRepository.createTour(tourModel);
        addTourFuture.thenAcceptAsync(result -> {
           tourModel.setTourID(result);
           if (result >= 0){
               listener.successAddTour(tourModel);
           }
        });
    }

    public interface addTourViewModelListener {
        public void successAddTour(TourModel tourModel);
    }
}
