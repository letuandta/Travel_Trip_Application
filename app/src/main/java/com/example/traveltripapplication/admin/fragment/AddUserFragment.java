package com.example.traveltripapplication.admin.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.traveltripapplication.R;
import com.example.traveltripapplication.databinding.FragmentUserAddBinding;
import com.example.traveltripapplication.model.UserModel;
import com.example.traveltripapplication.viewmodel.AddUserViewModel;

public class AddUserFragment extends DialogFragment implements AddUserViewModel.addUserViewModelListener {

    private FragmentUserAddBinding binding;

    public AddUserFragment(AddUserFragment.addUserViewModelListener addUserViewModelListener) {
        this.addUserViewModelListener = addUserViewModelListener;
    }

    private addUserViewModelListener addUserViewModelListener;

    public static AddUserFragment newInstanse(addUserViewModelListener addUserViewModelListener) {
        final AddUserFragment dialog = new AddUserFragment(addUserViewModelListener);
        return dialog;
    }
    public interface addUserViewModelListener {
        public void successAddUser(UserModel userModel);
    }

    AddUserViewModel addUserViewModel = new AddUserViewModel(this);
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.fragment_user_add, null,
                false);
        binding.setAdduser(addUserViewModel);
        com.example.traveltripapplication.util.dialog.DatePickerDialog datePickerDialog1
                = new com.example.traveltripapplication.util.dialog.DatePickerDialog(binding.datePickerBirthday, getContext());
        binding.datePickerBirthday.setOnClickListener(datePickerDialog1::openDatePicker);
        binding.clickBtn.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(getContext(), view);

            // Inflating popup menu from popup_menu.xml file
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                // Toast message on menu item clicked
                binding.clickBtn.setText(menuItem.getTitle());
                return true;
            });
            // Showing the popup menu
            popupMenu.show();
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
        super.onDismiss(dialog);
        addUserViewModelListener.successAddUser(addUserViewModel.getUserModel());
    }

    @Override
    public void successAddUser() {
        this.dismiss();
    }
}
