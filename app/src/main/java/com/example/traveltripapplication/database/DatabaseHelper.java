package com.example.traveltripapplication.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.traveltripapplication.database.category.CategoryHelper;
import com.example.traveltripapplication.database.contacts.ContactsHelper;
import com.example.traveltripapplication.database.permission.PermissionHelper;
import com.example.traveltripapplication.database.position.PositionHelper;
import com.example.traveltripapplication.database.rating.RatingHelper;
import com.example.traveltripapplication.database.state.StateHelper;
import com.example.traveltripapplication.database.tour.TourHelper;
import com.example.traveltripapplication.database.tour_category.TourCategoryHelper;
import com.example.traveltripapplication.database.tour_itinerary.TourItineraryHelper;
import com.example.traveltripapplication.database.tour_ticket.TourTicketHelper;
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

    public static TourCategoryHelper mTourCategoryHelper(){return TourCategoryHelper.getInstance(context);}
    public static TourTicketHelper mTourTicketHelper(){return TourTicketHelper.getInstance(context);}
    public static TourItineraryHelper mTourItineraryHelper(){return TourItineraryHelper.getInstance(context);}
    public static RatingHelper mRatingHelper(){return RatingHelper.getInstance(context);}

    public static void initDB(){
        try {
            //Tạp một instance trỏ đến db version của db muốn tạo
            SQLiteDatabase stateDB = new StateHelper(context).getWritableDatabase();

            //Khởi tạo table
            mPositionDBHelper().onCreate(stateDB);
            mPermissionHelper().onCreate(stateDB);
            mContactsHelper().onCreate(stateDB);
            mUserHelper().onCreate(stateDB);
            mCategoryHelper().onCreate(stateDB);
            mTourHelper().onCreate(stateDB);
            mTourCategoryHelper().onCreate(stateDB);
            mTourTicketHelper().onCreate(stateDB);
            mTourItineraryHelper().onCreate(stateDB);
            mRatingHelper().onCreate(stateDB);

            //Tạo dữ liệu mẫu không cần lo lặp dữ liệu
            mCategoryHelper().initDataTemplates();
            mTourHelper().initDataTemplates();
            mTourCategoryHelper().InitDataTemplates();
            mTourTicketHelper().InitDataTemplates();
            mTourItineraryHelper().initDataTemplates();
            mRatingHelper().initDataTemplates();


            //Đóng các intance lại
            mPositionDBHelper().close();
            mPermissionHelper().close();
            mContactsHelper().close();
            mUserHelper().close();
            //Đóng các instance triển khai templates data
            mCategoryHelper().close();
            mTourHelper().close();
            mTourCategoryHelper().close();
            mTourTicketHelper().close();
            mTourItineraryHelper().close();
            mRatingHelper().close();

            //Đóng StateDB
            stateDB.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
