package com.example.traveltripapplication.database.tour_itinerary;

import static com.example.traveltripapplication.database.tour_itinerary.TourItineraryContracts.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.database.DatabaseInformation;
import com.example.traveltripapplication.database.tour.TourContracts;
import com.example.traveltripapplication.model.TourItineraryModel;

import java.util.ArrayList;
import java.util.List;

public class TourItineraryHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = TourItineraryEntry.TABLE_NAME;
    private static final int DATABASE_VERSION = DatabaseInformation.VERSION;

    private static TourItineraryHelper INSTANCE;

    public TourItineraryHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static TourItineraryHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TourItineraryHelper(context);
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Tour Itinerary", "onCreate: tour itinerary");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + TourItineraryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TourItineraryEntry.TOUR_ID + " INTEGER, "
                + TourItineraryEntry.DAY + " INTEGER, "
                + TourItineraryEntry.CONTENT + " TEXT, "
                + "FOREIGN KEY ("+ TourItineraryEntry.TOUR_ID + ") REFERENCES " + TourContracts.TourEntry.TABLE_NAME + " ("+ TourContracts.TourEntry._ID +"));"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public long insert(TourItineraryModel tourItineraryModel){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TourItineraryEntry.TOUR_ID, tourItineraryModel.getTour_id());
        values.put(TourItineraryEntry.DAY, tourItineraryModel.getDay());
        values.put(TourItineraryEntry.CONTENT,tourItineraryModel.getContentRaw());
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public void initDataTemplates(){
        List<TourItineraryModel> tourItineraryModels = TourItineraryDataTemplates.getValues();
        tourItineraryModels.forEach(this::insert);
    }

    @SuppressLint("Range")
    public ArrayList<TourItineraryModel> getItineraryByTourId(long tourId){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<TourItineraryModel> tourItineraryModels = new ArrayList<>();
        String whereClause = TourItineraryEntry.TOUR_ID + " = ? ";
        String[] whereArgs = new String[]{String.valueOf(tourId)};
        Cursor cursor = db.query(TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        while (cursor.moveToNext()){
            tourItineraryModels.add(
                  new TourItineraryModel(
                          cursor.getLong(cursor.getColumnIndex(TourItineraryEntry._ID)),
                          cursor.getLong(cursor.getColumnIndex(TourItineraryEntry.TOUR_ID)),
                          cursor.getInt(cursor.getColumnIndex(TourItineraryEntry.DAY)),
                          cursor.getString(cursor.getColumnIndex(TourItineraryEntry.CONTENT))
                  )
            );
        }
        cursor.close();
        return tourItineraryModels;
    }
}
