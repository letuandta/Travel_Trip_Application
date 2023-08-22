package com.example.traveltripapplication.viewmodel.interfaceListener;

import com.example.traveltripapplication.model.UserModel;

public interface RegisterViewModelInterface {
    public void successRegister(UserModel user);

    public void errorRegister();
}
