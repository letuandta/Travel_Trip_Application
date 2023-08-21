package com.example.traveltripapplication.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.traveltripapplication.BR;
import com.example.traveltripapplication.model.TourModel;
import com.example.traveltripapplication.presenter.TourPresenter;

import java.util.concurrent.CompletableFuture;

public class DetailTourViewModel extends BaseObservable {

    private TourModel tour = new TourModel();

    public  ObservableField<String> rating = new ObservableField<>();

    public DetailTourViewModel(TourModel tourModel) {
        this.tour = tourModel;
        this.rating.set(String.valueOf(tourModel.getRatingTour()));
    }

    @Bindable
    public TourModel getTour() {
        return tour;
    }

    public void setTour(TourModel tour) {
        this.tour = tour;
        notifyPropertyChanged(BR.tour);
    }


}
