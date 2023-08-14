package com.example.traveltripapplication.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.traveltripapplication.database.category.CategoryHelper;
import com.example.traveltripapplication.database.contacts.ContactsHelper;
import com.example.traveltripapplication.database.permission.PermissionHelper;
import com.example.traveltripapplication.database.position.PositionHelper;
import com.example.traveltripapplication.database.state.StateHelper;
import com.example.traveltripapplication.database.tour.TourHelper;
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

    public static CategoryHelper mCategoryHelper(){return CategoryHelper.getInstance(context);}

    public static TourHelper mTourHelper(){return TourHelper.getInstance(context);}

    public static void initDB(){
        try {
            //Tạp một instance trỏ đến db version của db muốn tạo
            SQLiteDatabase stateDB = new StateHelper(context).getWritableDatabase();

            //Cách triển khai cũ
            mPositionDBHelper().onCreate(stateDB);
            mPermissionHelper().onCreate(stateDB);
            mContactsHelper().onCreate(stateDB);
            mUserHelper().onCreate(stateDB);

            //Đóng các intance lại
            mPositionDBHelper().close();
            mPermissionHelper().close();
            mContactsHelper().close();
            mUserHelper().close();

            //Cách triển khai mới
            mCategoryHelper().initDataTemplates(stateDB);
            mTourHelper().initDataTemplates(stateDB);

            //Đóng các instance
            mCategoryHelper().close();
            mTourHelper().close();

            //Đóng StateDB
            stateDB.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
