package com.example.traveltripapplication.data.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.traveltripapplication.data.database.order.order_state.OrderStateHelper;
import com.example.traveltripapplication.data.database.order.order_ticket.OrderTicketHelper;
import com.example.traveltripapplication.data.database.order.order_tour.OrderTourHelper;
import com.example.traveltripapplication.data.database.tour.category.CategoryHelper;
import com.example.traveltripapplication.data.database.tour.rating.RatingHelper;
import com.example.traveltripapplication.data.database.tour.tour.TourHelper;
import com.example.traveltripapplication.data.database.tour.tour_category.TourCategoryHelper;
import com.example.traveltripapplication.data.database.tour.tour_itinerary.TourItineraryHelper;
import com.example.traveltripapplication.data.database.tour.tour_ticket.TourTicketHelper;
import com.example.traveltripapplication.data.database.user.contacts.ContactsHelper;
import com.example.traveltripapplication.data.database.user.permission.PermissionHelper;
import com.example.traveltripapplication.data.database.user.position.PositionHelper;
import com.example.traveltripapplication.data.database.user.state.StateHelper;
import com.example.traveltripapplication.data.database.user.user.UserHelper;

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
    public static OrderTourHelper mOrderTourHelper(){return OrderTourHelper.getInstance(context);}
    public static OrderTicketHelper mOrderTicketHelper(){return OrderTicketHelper.getInstance(context);}
    public static OrderStateHelper mOrderStateHelper(){return OrderStateHelper.getInstance(context);}

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
            mOrderTourHelper().onCreate(stateDB);
            mOrderTicketHelper().onCreate(stateDB);
            mOrderStateHelper().onCreate(stateDB);

            //Tạo dữ liệu mẫu không cần lo lặp dữ liệu
            mCategoryHelper().initDataTemplates();
            mTourHelper().initDataTemplates();
            mTourCategoryHelper().InitDataTemplates();
            mTourTicketHelper().InitDataTemplates();
            mTourItineraryHelper().initDataTemplates();
            mRatingHelper().initDataTemplates();
            mOrderStateHelper().initDataTemplates();

            //Đóng StateDB
            stateDB.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
