package com.example.traveltripapplication.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltripapplication.AdminActivity;
import com.example.traveltripapplication.adapter.UserAdapter;
import com.example.traveltripapplication.databinding.FragmentUserBinding;
import com.example.traveltripapplication.model.UserModel;
import com.example.traveltripapplication.presenter.UserPresenter;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserFragment extends Fragment implements AddUserFragment.addUserViewModelListener {

    FragmentUserBinding fragmentUserBinding;

    UserAdapter userAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentUserBinding = FragmentUserBinding.inflate(inflater, container, false);
        CopyOnWriteArrayList<UserModel> listUser = new CopyOnWriteArrayList<>();
        CompletableFuture<ArrayList<UserModel>> completableFuture = UserPresenter.getListUser();
        completableFuture.thenAccept(listUser::addAll);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        userAdapter = new UserAdapter(listUser);
        fragmentUserBinding.rcListUser.setLayoutManager(null);
        fragmentUserBinding.rcListUser.setAdapter(null);
        fragmentUserBinding.rcListUser.setLayoutManager(layoutManager);
        fragmentUserBinding.rcListUser.setAdapter(userAdapter);
//        Log.d("ts", String.valueOf(listUser.size()));

        fragmentUserBinding.btnAdd.setOnClickListener(view -> {
            AddUserFragment addUserFragment = AddUserFragment.newInstanse(this);
            addUserFragment.show(requireActivity().getSupportFragmentManager(), "addUser");
        });
        return fragmentUserBinding.getRoot();
    }

    public void successAddUser() {
        if (((AdminActivity) requireActivity()).isFlag()){
            ((AdminActivity) requireActivity()).setFlag(false);
            CopyOnWriteArrayList<UserModel> listUser = new CopyOnWriteArrayList<>();
            CompletableFuture<ArrayList<UserModel>> completableFuture = UserPresenter.getListUser();
            completableFuture.thenAccept(listUser::addAll);
            userAdapter.onLoadData(listUser);
        }
    }
}
