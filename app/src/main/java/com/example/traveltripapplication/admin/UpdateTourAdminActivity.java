package com.example.traveltripapplication.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.admin.fragment.CateFragment;
import com.example.traveltripapplication.admin.fragment.TourFragment;
import com.example.traveltripapplication.data.database.DatabaseHelper;
import com.example.traveltripapplication.model.TourModel;

public class UpdateTourAdminActivity extends AppCompatActivity {
    Button btnSave;
    EditText editTourCode, editTourTitle, editDuration,
    editLocation, editExperience, editInfor;

    public TourModel tourModel;

    int position;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tour_admin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTourCode = findViewById(R.id.edit_tour_code);
        editTourTitle = findViewById(R.id.edit_tour_title);

        editDuration = findViewById(R.id.edit_tour_duration);

        editLocation = findViewById(R.id.edit_tour_location);
        editExperience = findViewById(R.id.edit_tour_experience);
        editInfor = findViewById(R.id.edit_tour_infor);
        if (getIntent().getExtras().getSerializable("tour") instanceof TourModel) {
            tourModel  = (TourModel) getIntent().getExtras().getSerializable("tour");
        }

        position = getIntent().getIntExtra("position", -1);

        editTourCode.setText(tourModel.getTourCode());
        editTourTitle.setText(tourModel.getTourTitle());

        editDuration.setText(String.valueOf(tourModel.getTourDuration()));

        editLocation.setText(tourModel.getTourLocation());
        editExperience.setText(tourModel.getExperience());
        editInfor.setText(tourModel.getMoreInfo());

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(view -> {
            this.updateTour();
        });
    }
    public void updateTour() {
        tourModel.setTourCode(String.valueOf(editTourCode.getText()));
        tourModel.setTourTitle(String.valueOf(editTourTitle.getText()));

        tourModel.setTourDuration(Integer.parseInt(String.valueOf(editDuration.getText())));

        tourModel.setTourLocation(String.valueOf(editLocation.getText()));
        tourModel.setExperience(String.valueOf(editExperience.getText()));
        tourModel.setMoreInfo(String.valueOf(editInfor.getText()));

        int data = DatabaseHelper.mTourHelper().update(tourModel);
        if (data >0 ) {
            Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            TourFragment.tourModelUpdate = tourModel;
            TourFragment.checkUpdateTour = true;
            TourFragment.position = position;
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}