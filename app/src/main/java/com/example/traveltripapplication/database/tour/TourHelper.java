package com.example.traveltripapplication.database.tour;

import static com.example.traveltripapplication.database.tour.TourContracts.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.traveltripapplication.database.DatabaseInformation;
import com.example.traveltripapplication.model.TourModel;

import java.util.ArrayList;
import java.util.List;

public class TourHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = DatabaseInformation.DATABASE_NAME;
    private static final String TABLE_NAME = TourEntry.TABLE_NAME;
    private static final int DATABASE_VERSION = DatabaseInformation.VERSION;

    private static TourHelper INSTANCE;

    public TourHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static TourHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TourHelper(context);
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("create tour", "onCreate: tour");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                        + TourEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + TourEntry.TOUR_CODE + " TEXT, "
                        + TourEntry.TITLE + " TEXT, "
                        + TourEntry.DURATION + " INTEGER, "
                        + TourEntry.LOCATION + " TEXT, "
                        + TourEntry.THUMBNAIL + " TEXT, "
                        + TourEntry.EXPERIENCE + " TEXT, "
                        + TourEntry.MORE_INFORMATION + " TEXT, "
                        + TourEntry.ACTIVE + " INTEGER);"
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
        TourModel tour = getTourByTourID(1);
        if(tour.getTourID() == -1) {
            List<TourModel> tourModels = TourDataTemplates.getValues();
            tourModels.forEach(this::insert);
        }
    }

    @SuppressLint("Range")
    public TourModel getTourByTourID(long id){
        SQLiteDatabase db = getReadableDatabase();
        TourModel tourModel = new TourModel();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + TourEntry._ID + " = ?;" , new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()) {
            tourModel.setTourID(cursor.getLong(cursor.getColumnIndex(TourEntry._ID)));
            tourModel.setTourTitle(cursor.getString(cursor.getColumnIndex(TourEntry.TITLE)));
            tourModel.setTourCode(cursor.getString(cursor.getColumnIndex(TourEntry.TOUR_CODE)));
            tourModel.setTourDuration(cursor.getInt(cursor.getColumnIndex(TourEntry.DURATION)));
            tourModel.setTourLocation(cursor.getString(cursor.getColumnIndex(TourEntry.LOCATION)));
            tourModel.setTourActive(cursor.getInt(cursor.getColumnIndex(TourEntry.ACTIVE)));
            tourModel.setThumbnail(cursor.getString(cursor.getColumnIndex(TourEntry.THUMBNAIL)));
            tourModel.setExperience(cursor.getString(cursor.getColumnIndex(TourEntry.EXPERIENCE)));
            tourModel.setMoreInfo(cursor.getString(cursor.getColumnIndex(TourEntry.MORE_INFORMATION)));
        }
        else {
            tourModel.setTourID(-1);
        }
        cursor.close();
        return tourModel;
    }

    //Hiện tại chưa có rating nên lấy 5 tour theo id
    @SuppressLint("Range")
    public ArrayList<TourModel> getTourHighRating(){
        ArrayList<TourModel> tourModels = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT t.* " +
                        "FROM tour as t " +
                        "LEFT JOIN rating as r " +
                        "ON t._id =  r.tour_id " +
                        "GROUP BY t._id " +
                        "ORDER BY avg(r.scores) DESC " +
                        "LIMIT 5", null);
        db.close();
        while (cursor.moveToNext()) {
            TourModel tourModel = new TourModel();
            tourModel.setTourID(cursor.getLong(cursor.getColumnIndex(TourEntry._ID)));
            tourModel.setTourTitle(cursor.getString(cursor.getColumnIndex(TourEntry.TITLE)));
            tourModel.setTourCode(cursor.getString(cursor.getColumnIndex(TourEntry.TOUR_CODE)));
            tourModel.setTourDuration(cursor.getInt(cursor.getColumnIndex(TourEntry.DURATION)));
            tourModel.setTourLocation(cursor.getString(cursor.getColumnIndex(TourEntry.LOCATION)));
            tourModel.setTourActive(cursor.getInt(cursor.getColumnIndex(TourEntry.ACTIVE)));
            tourModel.setThumbnail(cursor.getString(cursor.getColumnIndex(TourEntry.THUMBNAIL)));
            tourModel.setExperience(cursor.getString(cursor.getColumnIndex(TourEntry.EXPERIENCE)));
            tourModel.setMoreInfo(cursor.getString(cursor.getColumnIndex(TourEntry.MORE_INFORMATION)));
            tourModels.add(tourModel);
        }
        cursor.close();
        return tourModels;
    }

    @SuppressLint("Range")
    public ArrayList<TourModel> getTourByLocation(String location){
        ArrayList<TourModel> tourModels = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + TourEntry.LOCATION + " LIKE ?;", new String[]{"%"+location+"%"});
        db.close();
        while (cursor.moveToNext()) {
            TourModel tourModel = new TourModel();
            tourModel.setTourID(cursor.getLong(cursor.getColumnIndex(TourEntry._ID)));
            tourModel.setTourTitle(cursor.getString(cursor.getColumnIndex(TourEntry.TITLE)));
            tourModel.setTourCode(cursor.getString(cursor.getColumnIndex(TourEntry.TOUR_CODE)));
            tourModel.setTourDuration(cursor.getInt(cursor.getColumnIndex(TourEntry.DURATION)));
            tourModel.setTourLocation(cursor.getString(cursor.getColumnIndex(TourEntry.LOCATION)));
            tourModel.setTourActive(cursor.getInt(cursor.getColumnIndex(TourEntry.ACTIVE)));
            tourModel.setThumbnail(cursor.getString(cursor.getColumnIndex(TourEntry.THUMBNAIL)));
            tourModel.setExperience(cursor.getString(cursor.getColumnIndex(TourEntry.EXPERIENCE)));
            tourModel.setMoreInfo(cursor.getString(cursor.getColumnIndex(TourEntry.MORE_INFORMATION)));
            tourModels.add(tourModel);
        }
        cursor.close();
        return tourModels;
    }

    public boolean isTourCodeExists(String tourCode){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + TourEntry.TOUR_CODE + " = ?;", new String[]{tourCode});
        if(cursor.getCount() == 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public long insert(TourModel tourModel){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TourEntry.TOUR_CODE, tourModel.getTourCode());
        values.put(TourEntry.TITLE, tourModel.getTourTitle());
        values.put(TourEntry.DURATION, tourModel.getTourDuration());
        values.put(TourEntry.LOCATION, tourModel.getTourLocation());
        values.put(TourEntry.THUMBNAIL, tourModel.getThumbnail());
        values.put(TourEntry.ACTIVE, tourModel.getTourActive());
        values.put(TourEntry.EXPERIENCE, tourModel.getExperience());
        values.put(TourEntry.MORE_INFORMATION, tourModel.getMoreInfo());
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }
}
