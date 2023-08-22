package com.example.traveltripapplication.data.repository;

import com.example.traveltripapplication.data.database.DatabaseHelper;
import com.example.traveltripapplication.util.enumapp.LoginEnum;
import com.example.traveltripapplication.model.ContactsModel;
import com.example.traveltripapplication.model.UserModel;

import java.util.concurrent.CompletableFuture;

public class AuthRepository {




    //region for method
    public static CompletableFuture<UserModel> Login(String username, String password, LoginEnum loginEnum){
        CompletableFuture<UserModel> loginFuture = new CompletableFuture<>();
        UserModel user = new UserModel();
        if(loginEnum == LoginEnum.USERNAME){
            loginFuture = CompletableFuture.supplyAsync(() -> {
                return DatabaseHelper.mUserHelper().getUserByUsernameAndPassword(username, password);
            });
        }
        else if(loginEnum == LoginEnum.EMAIL){
            loginFuture = CompletableFuture.supplyAsync(() -> {
                return DatabaseHelper.mUserHelper().getUserByEmailAndPassword(username, password);
            });
        }
        return loginFuture;
    }

    public static CompletableFuture<Boolean> Logout(){
        CompletableFuture<Boolean> logoutFuture = new CompletableFuture<>();

        //region for logout thread

        logoutFuture.complete(true);
        return logoutFuture;
    }

    public static CompletableFuture<Integer> CompleteUserInfo(UserModel user, ContactsModel contactsModel){
        CompletableFuture<Integer> completableFuture = new CompletableFuture<Integer>();

        completableFuture = CompletableFuture.supplyAsync(() ->{
            long contacts_id = DatabaseHelper.mContactsHelper().insert(contactsModel);
            user.setContacts_id(contacts_id);
            return DatabaseHelper.mUserHelper().updateUser(user);
        });
        return completableFuture;
    }

    public static CompletableFuture<Long> Register(String fullName, String username, String password) {
        CompletableFuture<Long> registerFuture = new CompletableFuture<>();
        registerFuture = CompletableFuture.supplyAsync(() -> {
            return DatabaseHelper.mUserHelper().createAccount(fullName, username, password);
        });

        return registerFuture;
    }
}
