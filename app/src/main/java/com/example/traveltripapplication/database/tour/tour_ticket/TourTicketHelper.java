package com.example.traveltripapplication.database.tour.tour_ticket;

import static com.example.traveltripapplication.database.tour.tour_ticket.TourTicketContracts.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.database.DatabaseInformation;
import com.example.traveltripapplication.database.tour.tour.TourContracts;
import com.example.traveltripapplication.model.TourTicketModel;

import java.util.ArrayList;
import java.util.List;

public class TourTicketHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = TourTicketEntry.TABLE_NAME;
    private static final int DATABASE_VERSION = DatabaseInformation.VERSION;

    private static TourTicketHelper INSTANCE;

    public TourTicketHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static TourTicketHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TourTicketHelper(context);
        }
        return INSTANCE;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("tour ticket", "onCreate: tour ticket");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + TourTicketEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TourTicketEntry.TITLE + " TEXT, "
                + TourTicketEntry.DESCRIPTION + " TEXT, "
                + TourTicketEntry.TOUR_DATE + " TEXT, "
                + TourTicketEntry.REMAINING + " INTEGER, "
                + TourTicketEntry.AMOUNT + " INTEGER, "
                + TourTicketEntry.ACTIVE + " INTEGER, "
                + TourTicketEntry.PRICE + " TEXT, "
                + TourTicketEntry.TOUR_ID + " INTEGER, "
                + "FOREIGN KEY (" + TourTicketEntry.TOUR_ID + ") REFERENCES " + TourContracts.TourEntry.TABLE_NAME + " ("+ TourContracts.TourEntry._ID +"));"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void InitDataTemplates(){
        List<TourTicketModel> tourTicketModels = TourTicketDataTemplates.getValues();
        tourTicketModels.forEach(this::insert);
    }

    public long insert(TourTicketModel tourTicketModel){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TourTicketEntry.TOUR_ID, tourTicketModel.getTour_id());
        values.put(TourTicketEntry.TITLE, tourTicketModel.getTitle());
        values.put(TourTicketEntry.TOUR_DATE, tourTicketModel.getTour_date());
        values.put(TourTicketEntry.DESCRIPTION, tourTicketModel.getDescription());
        values.put(TourTicketEntry.ACTIVE, tourTicketModel.getActive());
        values.put(TourTicketEntry.REMAINING, tourTicketModel.getRemaining());
        values.put(TourTicketEntry.AMOUNT, tourTicketModel.getAmount());
        values.put(TourTicketEntry.PRICE, tourTicketModel.getPrice());
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    @SuppressLint("Range")
    public ArrayList<TourTicketModel> getTicketByTourID(long tourID){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<TourTicketModel> tourTicketModels = new ArrayList<>();
        String whereClause = TourTicketEntry.TOUR_ID + " = ? AND " + TourTicketEntry.ACTIVE + " = ? ";
        String[] whereArgs= new String[]{String.valueOf(tourID), "1"};
        Cursor cursor = db.query(TABLE_NAME, null, whereClause, whereArgs, null, null, null, null);
        while (cursor.moveToNext()){
            TourTicketModel tourTicketModel = new TourTicketModel();
            tourTicketModel.setId(cursor.getLong(cursor.getColumnIndex(TourTicketEntry._ID)));
            tourTicketModel.setTitle(cursor.getString(cursor.getColumnIndex(TourTicketEntry.TITLE)));
            tourTicketModel.setTour_date(cursor.getString(cursor.getColumnIndex(TourTicketEntry.TOUR_DATE)));
            tourTicketModel.setDescription(cursor.getString(cursor.getColumnIndex(TourTicketEntry.DESCRIPTION)));
            tourTicketModel.setActive(cursor.getInt(cursor.getColumnIndex(TourTicketEntry.ACTIVE)));
            tourTicketModel.setAmount(cursor.getInt(cursor.getColumnIndex(TourTicketEntry.AMOUNT)));
            tourTicketModel.setPrice(cursor.getString(cursor.getColumnIndex(TourTicketEntry.PRICE)));
            tourTicketModel.setRemaining(cursor.getInt(cursor.getColumnIndex(TourTicketEntry.REMAINING)));
            tourTicketModel.setTour_id(cursor.getLong(cursor.getColumnIndex(TourTicketEntry.TOUR_ID)));
            tourTicketModels.add(tourTicketModel);
        }
        cursor.close();
        return tourTicketModels;
    }
}
