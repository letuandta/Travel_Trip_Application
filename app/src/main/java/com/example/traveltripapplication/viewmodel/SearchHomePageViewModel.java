package com.example.traveltripapplication.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.traveltripapplication.BR;

import java.util.Calendar;

public class SearchHomePageViewModel extends BaseObservable {

    private String location;
    private String toDate;
    private String fromDate;
    private String numberPerson;
    private SearchHomePageListener listener;

    public SearchHomePageViewModel(SearchHomePageListener listener) {
        this.listener = listener;
        this.toDate = getToDaysDate();
        this.fromDate = getToDaysDate();
    }

    @Bindable
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        notifyPropertyChanged(BR.location);
    }

    @Bindable
    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
        notifyPropertyChanged(BR.toDate);
    }
    @Bindable
    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
        notifyPropertyChanged(BR.fromDate);
    }

    public SearchHomePageListener getListener() {
        return listener;
    }

    public void setListener(SearchHomePageListener listener) {
        this.listener = listener;
    }

    @Bindable
    public String getNumberPerson() {
        return numberPerson;
    }

    public void setNumberPerson(String numberPerson) {
        this.numberPerson = numberPerson;
        notifyPropertyChanged(BR.numberPerson);
    }

    public void subNumberPerson(){
        if(Integer.parseInt(getNumberPerson()) > 1)
        {
            setNumberPerson(String.valueOf(Integer.parseInt(getNumberPerson()) - 1));
        }
    }

    public void addNumberPerson(){
        if(getNumberPerson() == null) setNumberPerson("0");
        if(Integer.parseInt(getNumberPerson()) >= 0)
        {
            setNumberPerson(String.valueOf(Integer.parseInt(getNumberPerson()) + 1));
        }
    }
    public String getToDaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        month = month + 1;
        String dayString = String.valueOf(day);
        String monthString = String.valueOf(month);
        if(day < 10) dayString = "0" + dayString;
        if(month < 10) monthString = "0" + monthString;
        return dayString + "/" + monthString + "/" + year;
    }

    public interface SearchHomePageListener{
        public void onClickSearchButton();
        public void chooseToDay();
        public void choseFromDay();
    }
}
