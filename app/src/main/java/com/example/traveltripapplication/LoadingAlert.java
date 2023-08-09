package com.example.traveltripapplication;

import android.app.Activity;
import android.app.AlertDialog;

public class LoadingAlert {
    private Activity activity;
    private AlertDialog dialog;
    public LoadingAlert(Activity activity) {
        this.activity = activity;
    }

    void startDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setView(activity.getLayoutInflater().inflate(R.layout.loading_dialog, null));

        builder.setCancelable(true);

        dialog = builder.create();
        dialog.show();
    }

    void closeAlertDialog(){
        dialog.dismiss();
    }
}
