package com.example.traveltripapplication.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DatePickerDialog {

    private String date;
    private TextView textView;
    private final Context context;

    private android.app.DatePickerDialog datePickerDialog;

    public DatePickerDialog(TextView textView, Context context) {
        this.textView = textView;
        this.context = context;
        initDatePicker();
    }

    private void initDatePicker() {
        android.app.DatePickerDialog.OnDateSetListener dateSetListener = new android.app.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String dayString = String.valueOf(day);
                String monthString = String.valueOf(month);
                if(day < 10) dayString = "0" + dayString;
                if(month < 10) monthString = "0" + monthString;
                String date =  dayString + "/" + monthString + "/" + year;
                getTextView().setText(String.valueOf(date));
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        this.datePickerDialog = new android.app.DatePickerDialog(context, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    public void openDatePicker(View view){
        this.datePickerDialog.show();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public Context getContext() {
        return context;
    }

    public android.app.DatePickerDialog getDatePickerDialog() {
        return datePickerDialog;
    }

    public void setDatePickerDialog(android.app.DatePickerDialog datePickerDialog) {
        this.datePickerDialog = datePickerDialog;
    }
}
