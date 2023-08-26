package com.example.traveltripapplication.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.admin.fragment.CateFragment;
import com.example.traveltripapplication.data.database.DatabaseHelper;
import com.example.traveltripapplication.model.CategoryModel;

public class UpdateCateAdminActivity extends AppCompatActivity {

    Button btnSave;

    EditText editCateName, editCateCode;

    public CategoryModel categoryModel;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cate_admin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editCateName = findViewById(R.id.edit_cate_name);
        editCateCode = findViewById(R.id.edit_cate_code);
        if (getIntent().getExtras().getSerializable("cate") instanceof CategoryModel) {
            categoryModel = (CategoryModel) getIntent().getExtras().getSerializable("cate");
        }
        position = getIntent().getIntExtra("position", -1);

        editCateCode.setText(categoryModel.getCateCode());
        editCateName.setText(categoryModel.getCateName());

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(view -> {
            this.updateCate();
        });
    }

    public void updateCate() {
        categoryModel.setCateCode(String.valueOf(editCateCode.getText()));
        categoryModel.setCateName(String.valueOf(editCateName.getText()));

        int data = DatabaseHelper.mCategoryHelper().update(categoryModel);
        if (data >0 ) {
            Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            CateFragment.categoryModelUpdate = categoryModel;
            CateFragment.checkUpdateCate = true;
            CateFragment.position = position;
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}