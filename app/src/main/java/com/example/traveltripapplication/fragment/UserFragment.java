package com.example.traveltripapplication.fragment;

import android.content.Context;
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

//        Log.d("ts", String.valueOf(listUser.size()));
        return fragmentUserBinding.getRoot();
    }

    public void successAddUser() {
        if (((AdminActivity) requireActivity()).isFlag()){
            ((AdminActivity) requireActivity()).setFlag(false);
            ArrayList<UserModel> listUser = new ArrayList<>();
            CompletableFuture<ArrayList<UserModel>> completableFuture = UserPresenter.getListUser();
            completableFuture.thenAccept(listUser::addAll);
            userAdapter.onLoadData(listUser);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<UserModel> listUser = new ArrayList<>();
        listUser.add(new UserModel());
        CompletableFuture<ArrayList<UserModel>> completableFuture = UserPresenter.getListUser();
        completableFuture.thenAccept(result -> {
            listUser.clear();
            listUser.addAll(result);
        });
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        userAdapter = new UserAdapter(listUser);
        fragmentUserBinding.rcListUser.setAdapter(userAdapter);
        fragmentUserBinding.rcListUser.setLayoutManager(linearLayoutManager);
        fragmentUserBinding.btnAdd.setOnClickListener(view1 -> {
            AddUserFragment addUserFragment = AddUserFragment.newInstanse(this);
            addUserFragment.show(requireActivity().getSupportFragmentManager(), "addUser");
        });
    }

    //    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if (((AdminActivity) requireActivity()).isFlag()){
//            ((AdminActivity) requireActivity()).setFlag(false);
//            ArrayList<UserModel> listUser = new ArrayList<>();
//            CompletableFuture<ArrayList<UserModel>> completableFuture = UserPresenter.getListUser();
//            completableFuture.thenAccept(listUser::addAll);
//            userAdapter.onLoadData(listUser);
//            fragmentUserBinding.rcListUser.smoothScrollToPosition(0);
//        }
//
//    }
}
