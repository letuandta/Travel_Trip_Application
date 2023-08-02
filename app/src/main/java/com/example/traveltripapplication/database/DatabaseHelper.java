package com.example.traveltripapplication.database;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.traveltripapplication.database.permission.PermissionContract;
import com.example.traveltripapplication.database.permission.PermissionHelper;
import com.example.traveltripapplication.database.position.PositionHelper;

public class DatabaseHelper {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static void setContext(Context context){
        DatabaseHelper.context = context;
    }

    public static PositionHelper mPositionDBHelper(){
        return PositionHelper.getInstance(context);
    }

    public static PermissionHelper mPermissionHelper(){
        return PermissionHelper.getInstance(context);
    }

}
