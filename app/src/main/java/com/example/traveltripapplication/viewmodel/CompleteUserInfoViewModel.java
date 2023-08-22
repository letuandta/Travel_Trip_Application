package com.example.traveltripapplication.viewmodel;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.traveltripapplication.BR;
import com.example.traveltripapplication.viewmodel.interfaceListener.CompleteUserInfoInterface;
import com.example.traveltripapplication.model.ContactsModel;
import com.example.traveltripapplication.model.UserModel;
import com.example.traveltripapplication.data.repository.AuthRepository;

import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

public class CompleteUserInfoViewModel extends BaseObservable {
    private ContactsModel contactsModel = new ContactsModel();
    private UserModel user;

    ObservableField<Boolean> saving = new ObservableField<>(false);

    public CompleteUserInfoViewModel(UserModel user) {
        this.user = user;
        this.user.setBirthday(getToDaysDate());
    }

    @Bindable
    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
        notifyPropertyChanged(BR.user);
    }

    @Bindable
    public ContactsModel getContactsModel() {
        return contactsModel;
    }

    public void setContactsModel(ContactsModel contactsModel) {
        this.contactsModel = contactsModel;
        notifyPropertyChanged(BR.contactsModel);
    }

    public void onClickSaveButton(CompleteUserInfoInterface completeUserInfoInterface){
        CompletableFuture<Integer> completableFuture = AuthRepository.CompleteUserInfo(user, contactsModel);
        completableFuture.thenAcceptAsync(result -> {
            Log.d("id dd", String.valueOf(result));
            if(result > 0) {
                completeUserInfoInterface.saveSuccess();
            }
            else {
                completeUserInfoInterface.saveError();
            }
        });
        completableFuture.thenRun(() -> {
            completeUserInfoInterface.saving();
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
}
