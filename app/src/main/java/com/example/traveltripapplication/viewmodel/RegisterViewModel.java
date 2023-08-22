package com.example.traveltripapplication.viewmodel;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.example.traveltripapplication.BR;
import com.example.traveltripapplication.viewmodel.interfaceListener.RegisterViewModelInterface;
import com.example.traveltripapplication.model.RegisterModel;
import com.example.traveltripapplication.model.UserModel;
import com.example.traveltripapplication.data.repository.AuthRepository;

import java.util.concurrent.CompletableFuture;

public class RegisterViewModel extends BaseObservable {
    private RegisterModel mRegisterModel = new RegisterModel();
    public ObservableField<String> message = new ObservableField<>();

    @Bindable
    public RegisterModel getRegisterModel() {
        return mRegisterModel;
    }

    public void setRegisterModel(RegisterModel mRegisterModel) {
        this.mRegisterModel = mRegisterModel;
        notifyPropertyChanged(BR.registerModel);
    }

    public void onClickButtonRegister(RegisterViewModelInterface registerViewModelInterface) {
        if ((getRegisterModel().checkPassword()) && getRegisterModel().isUserNameValid() && getRegisterModel().isPasswordValid() && getRegisterModel().isConfirmPasswordValid()) {
            Log.d("Account", getRegisterModel().getFullName() + getRegisterModel().getPassword() + getRegisterModel().getUsername() + getRegisterModel().getConfirmPassword());
            CompletableFuture<Long> registerFuture = AuthRepository.Register(getRegisterModel().getFullName(), getRegisterModel().getUsername(), getRegisterModel().getPassword());

            registerFuture.thenAcceptAsync(result -> {
                if (result >= 0) {
                    message.set("Dang ky thanh cong");
                    UserModel user = new UserModel();
                    user.set_ID(result);
                    user.setFull_name(getRegisterModel().getFullName());
                    user.setAvatar("https://i.pinimg.com/564x/40/98/2a/40982a8167f0a53dedce3731178f2ef5.jpg");
                    registerViewModelInterface.successRegister(user);
                } else {
                    message.set("Tai khoan da ton tai");
                }
            });
            registerFuture.thenRun(() -> {
                message.set("Cho doi");
            });
        }
        else {
            message.set("username or password invalid");
        }
    }


}
