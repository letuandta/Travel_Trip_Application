package com.example.traveltripapplication.data.database.tour.tour_itinerary;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.data.database.DatabaseInformation;
import com.example.traveltripapplication.data.database.tour.tour.TourContracts;
import com.example.traveltripapplication.model.TourItineraryModel;

import java.util.ArrayList;
import java.util.List;

public class TourItineraryHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = TourItineraryContracts.TourItineraryEntry.TABLE_NAME;
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
                + TourItineraryContracts.TourItineraryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TourItineraryContracts.TourItineraryEntry.TOUR_ID + " INTEGER, "
                + TourItineraryContracts.TourItineraryEntry.DAY + " INTEGER, "
                + TourItineraryContracts.TourItineraryEntry.CONTENT + " TEXT, "
                + "FOREIGN KEY ("+ TourItineraryContracts.TourItineraryEntry.TOUR_ID + ") REFERENCES " + TourContracts.TourEntry.TABLE_NAME + " ("+ TourContracts.TourEntry._ID +"));"
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
        values.put(TourItineraryContracts.TourItineraryEntry.TOUR_ID, tourItineraryModel.getTour_id());
        values.put(TourItineraryContracts.TourItineraryEntry.DAY, tourItineraryModel.getDay());
        values.put(TourItineraryContracts.TourItineraryEntry.CONTENT,tourItineraryModel.getContentRaw());
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public void initDataTemplates(){
        List<TourItineraryModel> tourItineraryModels = TourItineraryContracts.TourItineraryDataTemplates.getValues();
        tourItineraryModels.forEach(this::insert);
    }

    @SuppressLint("Range")
    public ArrayList<TourItineraryModel> getItineraryByTourId(long tourId){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<TourItineraryModel> tourItineraryModels = new ArrayList<>();
        String whereClause = TourItineraryContracts.TourItineraryEntry.TOUR_ID + " = ? ";
        String[] whereArgs = new String[]{String.valueOf(tourId)};
        Cursor cursor = db.query(TABLE_NAME, null, whereClause, whereArgs, null, null, null);
        while (cursor.moveToNext()){
            tourItineraryModels.add(
                  new TourItineraryModel(
                          cursor.getLong(cursor.getColumnIndex(TourItineraryContracts.TourItineraryEntry._ID)),
                          cursor.getLong(cursor.getColumnIndex(TourItineraryContracts.TourItineraryEntry.TOUR_ID)),
                          cursor.getInt(cursor.getColumnIndex(TourItineraryContracts.TourItineraryEntry.DAY)),
                          cursor.getString(cursor.getColumnIndex(TourItineraryContracts.TourItineraryEntry.CONTENT))
                  )
            );
        }
        cursor.close();
        return tourItineraryModels;
    }
}
