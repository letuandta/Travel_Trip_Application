package com.example.traveltripapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailTourActivity extends AppCompatActivity {
    TextView value;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tour);
//        value = (TextView) findViewById(R.id.value);
    }

    public void increment(View v ) {
        count++;
        value.setText("" + count);
    }
    public void decrement(View v) {
        if (count <= 0) count = 0;
        else count--;
        value.setText(""+count);
    }
}