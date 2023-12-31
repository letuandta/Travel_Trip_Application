package com.example.traveltripapplication.data.database.order.order_state;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.data.database.DatabaseInformation;

import java.util.List;

public class OrderStateHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = OrderStateContracts.OrderStateEntry.TABLE_NAME;
    private static final int DATABASE_VERSION = DatabaseInformation.VERSION;

    private static OrderStateHelper INSTANCE;

    public OrderStateHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static OrderStateHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new OrderStateHelper(context);
        }
        return INSTANCE;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("create order sate ", "onCreate: ordersate");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + OrderStateContracts.OrderStateEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + OrderStateContracts.OrderStateEntry.STATE_NAME + " TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void initDataTemplates(){
        SQLiteDatabase db = getWritableDatabase();
        List<OrderStateContracts.OrderStateModel> list = OrderStateContracts.OrderStateDataTemplates.getValues();
        list.forEach(item -> {
            ContentValues contentValues = new ContentValues();
            contentValues.put(OrderStateContracts.OrderStateEntry.STATE_NAME, item.getStateName());
            db.insert(TABLE_NAME, null, contentValues);
        });
        db.close();
    }
}
