package com.example.traveltripapplication.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.traveltripapplication.database.contacts.ContactsHelper;
import com.example.traveltripapplication.database.permission.PermissionHelper;
import com.example.traveltripapplication.database.position.PositionHelper;
import com.example.traveltripapplication.database.state.StateHelper;
import com.example.traveltripapplication.database.user.UserHelper;

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

    public static ContactsHelper mContactsHelper(){
        return ContactsHelper.getInstance(context);
    }

    public static UserHelper mUserHelper(){
        return UserHelper.getInstance(context);
    }

    public static void initDB(){
        try {
            SQLiteDatabase stateDB = new StateHelper(context).getWritableDatabase();
            mPositionDBHelper().onCreate(stateDB);
            mPermissionHelper().onCreate(stateDB);
            mContactsHelper().onCreate(stateDB);
            mUserHelper().onCreate(stateDB);
            stateDB.close();
            mPositionDBHelper().close();
            mPermissionHelper().close();
            mContactsHelper().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
