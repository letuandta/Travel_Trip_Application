package com.example.traveltripapplication.data.database.order.order_ticket;

import static com.example.traveltripapplication.data.database.order.order_ticket.OrderTicketContracts.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.data.database.tour.tour_ticket.TourTicketContracts;
import com.example.traveltripapplication.data.database.DatabaseInformation;
import com.example.traveltripapplication.data.database.order.order_tour.OrderTourContracts;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class OrderTicketHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = OrderTicketEntry.TABLE_NAME;
    private static final int DATABASE_VERSION = DatabaseInformation.VERSION;

    private static OrderTicketHelper INSTANCE;

    public OrderTicketHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static OrderTicketHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new OrderTicketHelper(context);
        }
        return INSTANCE;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("create order ticket", "onCreate: order_ticket");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + OrderTicketEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + OrderTicketEntry.ORDER_ID + " INTEGER, "
                + OrderTicketEntry.TOUR_TICKET_ID + " INTEGER, "
                + OrderTicketEntry.QUANTITY + " INTEGER, "
                + "FOREIGN KEY ("+ OrderTicketEntry.ORDER_ID+") REFERENCES " + OrderTourContracts.OrderEntry.TABLE_NAME + "("+ OrderTourContracts.OrderEntry._ID +"), "
                + "FOREIGN KEY ("+ OrderTicketEntry.TOUR_TICKET_ID+") REFERENCES " + TourTicketContracts.TourTicketEntry.TABLE_NAME + "("+ TourTicketContracts.TourTicketEntry._ID +"));"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public long insert(long orderId, Map<Long, Integer> tickets){
        SQLiteDatabase db = getWritableDatabase();
        final long[] id = {-1};
        tickets.forEach((ticketID, quantity) -> {
            ContentValues values = new ContentValues();
            values.put(OrderTicketEntry.ORDER_ID, orderId);
            values.put(OrderTicketEntry.TOUR_TICKET_ID, ticketID);
            values.put(OrderTicketEntry.QUANTITY, quantity);
            id[0] = db.insert(TABLE_NAME, OrderTicketEntry._ID, values);
        });
        db.close();
        return id[0];
    }
}
