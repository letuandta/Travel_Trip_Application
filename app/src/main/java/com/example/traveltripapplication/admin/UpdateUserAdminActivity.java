package com.example.traveltripapplication.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.database.DatabaseHelper;
import com.example.traveltripapplication.databinding.FragmentUserBinding;
import com.example.traveltripapplication.model.ContactsModel;
import com.example.traveltripapplication.model.UserModel;

public class UpdateUserAdminActivity extends AppCompatActivity {
    Button backButton, btnDatePickerBirthday, btnRole, btnSave;
    FragmentUserBinding fragmentUserBinding;

    public UserModel userModel;
    EditText editFullName, editEmail, editContact, editAddress,
    editMore;

    public ContactsModel contactsModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_admin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnDatePickerBirthday = findViewById(R.id.datePickerBirthday);
        com.example.traveltripapplication.dialog.DatePickerDialog datePickerDialog1
                = new com.example.traveltripapplication.dialog.DatePickerDialog(btnDatePickerBirthday, this);
        btnDatePickerBirthday.setOnClickListener(datePickerDialog1::openDatePicker);
        btnRole = findViewById(R.id.clickBtn);
        btnRole.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(this, view);

            // Inflating popup menu from popup_menu.xml file
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                // Toast message on menu item clicked
                btnRole.setText(menuItem.getTitle());
                return true;
            });
            // Showing the popup menu
            popupMenu.show();
        });

        editFullName = findViewById(R.id.edit_fullname);
        editEmail = findViewById(R.id.edit_email);
        editContact = findViewById(R.id.edit_contact);
        editAddress = findViewById(R.id.edit_address);
        editMore = findViewById(R.id.edit_more);

        if (getIntent().getExtras().getSerializable("user") instanceof UserModel) {
            userModel = (UserModel) getIntent().getExtras().getSerializable("user");
        }
        contactsModel = DatabaseHelper.mContactsHelper().getContactsById(userModel.getContacts_id());
        editFullName.setText(userModel.getFull_name());
        editEmail.setText(userModel.getEmail());
        editContact.setText(contactsModel.getPhone_number());
        editAddress.setText(contactsModel.getAddress());
        editMore.setText(contactsModel.getMore());

        btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(view -> {
            this.updateUser();
        });
        if (userModel.getIs_super_user() == 1) {

            btnRole.setText("Admin");
        }
        else   btnRole.setText("Customer");
//        backButton = findViewById(R.id.btn_back);
//
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }

    public void updateUser() {
        userModel.setFull_name(String.valueOf(editFullName.getText()));
        userModel.setBirthday(String.valueOf(btnDatePickerBirthday.getText()));
        userModel.setEmail(String.valueOf(editEmail.getText()));
        contactsModel.setAddress(String.valueOf(editAddress.getText()));
        contactsModel.setMore(String.valueOf(editMore.getText()));
        contactsModel.setPhone_number(String.valueOf(editContact.getText()));
        if (btnRole.getText() == "Admin") {
            userModel.setIs_super_user(1);
        }
        else  userModel.setIs_super_user(0);
        int data = DatabaseHelper.mUserHelper().updateUser(userModel);
        int data2 = DatabaseHelper.mContactsHelper().update(contactsModel, userModel.getContacts_id());

        if (data > 0 && data2 >0) {
            Toast.makeText(this, "Thanhf cong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}