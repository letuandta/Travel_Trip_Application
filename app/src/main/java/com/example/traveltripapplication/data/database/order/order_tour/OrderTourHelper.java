package com.example.traveltripapplication.data.database.order.order_tour;

import static com.example.traveltripapplication.data.database.order.order_tour.OrderTourContracts.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.data.database.user.user.UserContract;
import com.example.traveltripapplication.data.database.DatabaseInformation;
import com.example.traveltripapplication.data.database.order.order_state.OrderStateContracts;
import com.example.traveltripapplication.data.database.tour.tour.TourContracts;
import com.example.traveltripapplication.model.OrderModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();


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
                + "FOREIGN KEY ("+ OrderEntry.USER_ID+") REFERENCES " + UserContract.UserEntry.TABLE_NAME + "("+ UserContract.UserEntry._ID +"), "
                + "FOREIGN KEY ("+ OrderEntry.TOUR_ID+") REFERENCES " + TourContracts.TourEntry.TABLE_NAME + "("+ TourContracts.TourEntry._ID +"), "
                + "FOREIGN KEY ("+ OrderEntry.ORDER_STATE_ID+") REFERENCES " + OrderStateContracts.OrderStateEntry.TABLE_NAME + "("+ OrderStateContracts.OrderStateEntry._ID +"));"
                    );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public long insert(OrderModel orderModel){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderEntry.TOUR_ID, orderModel.getTour_id());
        values.put(OrderEntry.USER_ID, orderModel.getUser_id());
        values.put(OrderEntry.ORDER_STATE_ID, 1);
        values.put(OrderEntry.ORDER_DATE, String.valueOf(dtf.format(now)));
        values.put(OrderEntry.ADDRESS, orderModel.getAddress());
        values.put(OrderEntry.PHONE, orderModel.getPhone());
        values.put(OrderEntry.CUSTOMER_NAME, orderModel.getCustomer_name());
        long id = db.insert(TABLE_NAME, OrderEntry._ID, values);
        db.close();
        return id;
    }

    @SuppressLint("Range")
    public ArrayList<OrderModel> getOrderByUserId(long userId){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<OrderModel> orderModels = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT o.*, t.title\n" +
                        "FROM order_tour as o\n" +
                        "INNER JOIN tour as t\n" +
                        "ON t._id = o._id\n" +
                        "WHERE user_id = ?",
                new String[]{String.valueOf(userId)});
        while (cursor.moveToNext()){
            OrderModel orderModel = new OrderModel();
            orderModel.set_id(cursor.getLong(cursor.getColumnIndex(OrderEntry._ID)));
            orderModel.setTour_id(cursor.getLong(cursor.getColumnIndex(OrderEntry.TOUR_ID)));
            orderModel.setUser_id(cursor.getLong(cursor.getColumnIndex(OrderEntry.USER_ID)));
            orderModel.setOrder_date(cursor.getString(cursor.getColumnIndex(OrderEntry.ORDER_DATE)));
            orderModel.setOrder_state_id(cursor.getInt(cursor.getColumnIndex(OrderEntry.ORDER_STATE_ID)));
            orderModel.setAddress(cursor.getString(cursor.getColumnIndex(OrderEntry.ADDRESS)));
            orderModel.setPhone(cursor.getString(cursor.getColumnIndex(OrderEntry.PHONE)));
            orderModel.setCustomer_name(cursor.getString(cursor.getColumnIndex(OrderEntry.CUSTOMER_NAME)));
            orderModel.setTour_title(cursor.getString(cursor.getColumnIndex(TourContracts.TourEntry.TITLE)));
            orderModels.add(orderModel);
        }
        cursor.close();
        return orderModels;
    }

    public int changeState(long orderId, int stateId){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderEntry.ORDER_STATE_ID, stateId);
        int id = db.update(TABLE_NAME, values, OrderEntry._ID + " = ?", new String[]{String.valueOf(orderId)});
        db.close();
        return id;
    }
}
