package com.example.traveltripapplication.database.order.order_tour;

import static com.example.traveltripapplication.database.order.order_tour.OrderTourContracts.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.database.DatabaseInformation;
import com.example.traveltripapplication.database.order.order_state.OrderStateContracts;
import com.example.traveltripapplication.database.tour.tour.TourContracts;
import com.example.traveltripapplication.database.user.user.UserContract;

public class OrderTourHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = OrderEntry.TABLE_NAME;
    private static final int DATABASE_VERSION = DatabaseInformation.VERSION;

    private static OrderTourHelper INSTANCE;

    public OrderTourHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static OrderTourHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new OrderTourHelper(context);
        }
        return INSTANCE;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("create order", "onCreate: order_tour");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + OrderEntry.USER_ID + " INTEGER, "
                + OrderEntry.TOUR_ID + " INTEGER, "
                + OrderEntry.ORDER_DATE + " TEXT, "
                + OrderEntry.ORDER_STATE_ID + " INTEGER, "
                + OrderEntry.ADDRESS + " TEXT, "
                + OrderEntry.CUSTOMER_NAME + " TEXT,"
                + OrderEntry.PHONE + " TEXT, "
                + "FOREIGN KEY ("+OrderEntry.USER_ID+") REFERENCES " + UserContract.UserEntry.TABLE_NAME + "("+ UserContract.UserEntry._ID +"), "
                + "FOREIGN KEY ("+OrderEntry.TOUR_ID+") REFERENCES " + TourContracts.TourEntry.TABLE_NAME + "("+ TourContracts.TourEntry._ID +"), "
                + "FOREIGN KEY ("+OrderEntry.ORDER_STATE_ID+") REFERENCES " + OrderStateContracts.OrderStateEntry.TABLE_NAME + "("+ OrderStateContracts.OrderStateEntry._ID +"));"
                    );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
