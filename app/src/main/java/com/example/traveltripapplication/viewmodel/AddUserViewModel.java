package com.example.traveltripapplication.viewmodel;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.traveltripapplication.BR;
import com.example.traveltripapplication.model.ContactsModel;
import com.example.traveltripapplication.model.UserModel;
import com.example.traveltripapplication.data.repository.AuthRepository;

import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

public class AddUserViewModel extends BaseObservable {
    private UserModel userModel = new UserModel();
    private ContactsModel contactsModel = new ContactsModel();

    private addUserViewModelListener listener ;

    public final ObservableField<String> role = new ObservableField<>("admin");



    @Bindable
    public ContactsModel getContactsModel() {
        return contactsModel;
    }

    public void setContactsModel(ContactsModel contactsModel) {
        this.contactsModel = contactsModel;
        notifyPropertyChanged(BR.contactsModel);
    }

    @Bindable
    public UserModel getUserModel() {
        return userModel;
    }
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
        notifyPropertyChanged(BR.userModel);
    }

    public AddUserViewModel(addUserViewModelListener listener) {
        this.userModel.setBirthday(getToDaysDate());
        this.listener = listener;
    }

    public void onClickButtonAdd() {
        if (role.get() == "admin") {
            userModel.setIs_super_user(1);
        }
        else
            userModel.setIs_super_user(0);
        CompletableFuture<Long> addUserFuture = AuthRepository.Register(userModel.getFull_name(), userModel.getUsername(), userModel.getPassword());
        addUserFuture.thenAcceptAsync(result -> {
            userModel.set_ID(result);
            if (result >= 0) {
                CompletableFuture<Integer> completableFuture = AuthRepository.CompleteUserInfo(userModel, contactsModel);
                completableFuture.thenAccept(re -> {
                    Log.d("id dd", String.valueOf(re));
                    if(re > 0) {
                        listener.successAddUser();
                    }
                });
            }
        });
    }
    public String getToDaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        month = month + 1;
        return day + "/" + month + "/" + year;
    }

    public interface addUserViewModelListener {
        public void successAddUser();
    }
}
